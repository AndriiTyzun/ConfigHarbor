package ua.nulp.configharbor.service.implementation;

import org.springframework.stereotype.Service;
import ua.nulp.configharbor.model.users.User;
import ua.nulp.configharbor.repository.UserRepository;
import ua.nulp.configharbor.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByEmail(String email) throws Exception {
        return userRepository.getUserByUserEmail(email).orElseThrow(() -> new Exception("Invalid credentials"));
    }

    public void addUser(User user) throws Exception {
        //userRepository.save(user);
        System.out.println("New USER!");
    }
}
