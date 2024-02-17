package ua.nulp.configharbor.service.implementation;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import ua.nulp.configharbor.model.users.User;
import ua.nulp.configharbor.service.UserService;


@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserService userService;

    public UserDetailServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = null;
        try {
            user = userService.getUserByEmail(username);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return user;
    }
}
