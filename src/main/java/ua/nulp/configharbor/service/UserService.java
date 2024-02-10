package ua.nulp.configharbor.service;

import ua.nulp.configharbor.model.users.User;

import java.util.Optional;

public interface UserService {
    public User getUserByEmail(String email) throws Exception;
}
