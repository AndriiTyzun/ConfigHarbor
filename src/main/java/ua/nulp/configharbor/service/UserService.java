package ua.nulp.configharbor.service;

import ua.nulp.configharbor.model.users.User;

public interface UserService {
    User getUserByEmail(String email) throws Exception;

    void addUser(User user) throws Exception;
}
