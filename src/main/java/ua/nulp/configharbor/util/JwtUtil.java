package ua.nulp.configharbor.util;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import ua.nulp.configharbor.model.users.User;

import javax.crypto.SecretKey;
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
    private final SecretKey secret;
    private final JwtParser jwtParser;

    public JwtUtil(){
        secret = Jwts.SIG.HS256.key().build();
        jwtParser = Jwts.parser().verifyWith(secret).build();
    }

    public String createToken(User user) {
        Map<String, String> claims = new HashMap<>();
        claims.put("firstName",user.getUserFirstName());
        claims.put("lastName",user.getUserLastName());
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(JWT_TOKEN_VALIDITY));
        return Jwts.builder()
                .claims(claims)
                .expiration(tokenValidity)
                .signWith(secret)
                .compact();
    }

    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("Expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            req.setAttribute("Invalid", ex.getMessage());
            throw ex;
        }
    }

    private Claims parseJwtClaims(String token) {
        return jwtParser
                .parseSignedClaims(token)
                .getPayload();
    }

    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    public boolean validateClaims(Claims claims) throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            throw e;
        }
    }

    public String getEmail(String token) {
        return Jwts.parser()
                .verifyWith(secret)
                .build()
                .parseSignedClaims(token)
                .getPayload().getSubject();
    }

    private List<String> getRoles(String token) {
        return (List<String>)
                Jwts.parser()
                .verifyWith(secret)
                .build()
                .parseSignedClaims(token)
                .getPayload().get("roles");
    }
}
