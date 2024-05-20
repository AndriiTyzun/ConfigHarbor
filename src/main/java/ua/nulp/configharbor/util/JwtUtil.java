package ua.nulp.configharbor.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ua.nulp.configharbor.model.users.User;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;
    private final JwtParser jwtParser;
    private final SecretKey secretKey;

    public JwtUtil() {
        String secret = "5pAq6zRyX8bC3dV2wS7gN1mK9jF0hL4tUoP6iBvE3nG8xZaQrY7cW2fA";
        secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));

        jwtParser = Jwts.parserBuilder().setSigningKey(secretKey).build();
    }

    public String createToken(User user) {
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(JWT_TOKEN_VALIDITY));
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("firstName",user.getUserFirstName())
                .claim("lastName",user.getUserLastName())
                .setExpiration(tokenValidity)
                .signWith(secretKey)
                .compact();
    }

//    public Claims resolveClaims(HttpServletRequest req) {
//        try {
//            String token = resolveToken(req);
//            if (token != null) {
//                return parseJwtClaims(token);
//            }
//            return null;
//        } catch (ExpiredJwtException ex) {
//            req.setAttribute("Expired", ex.getMessage());
//            throw ex;
//        } catch (Exception ex) {
//            req.setAttribute("Invalid", ex.getMessage());
//            throw ex;
//        }
//    }

    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }


    public String getEmail(String token) {
        return jwtParser
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    private List<String> getRoles(String token) {
        return (List<String>) jwtParser
                .parseClaimsJws(token)
                .getBody().get("roles");
    }
}
