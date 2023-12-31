package bcsd.BoardAPI.service;

import bcsd.BoardAPI.domain.User;
import bcsd.BoardAPI.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User insertUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserByUserId(String userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void updateUserName(String userId, String userName) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            existingUser.setUserName(userName);
            userRepository.save(existingUser);
        }
    }

    @Override
    public void updateUserPw(String userId, String userPw) {
        User existingUser = userRepository.findById(userId).orElse(null);
        if (existingUser != null) {
            existingUser.setUserPw(userPw);
            userRepository.save(existingUser);
        }
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }
}
