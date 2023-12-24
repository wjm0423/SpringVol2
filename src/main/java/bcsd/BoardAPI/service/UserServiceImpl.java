package bcsd.BoardAPI.service;

import bcsd.BoardAPI.domain.User;
import bcsd.BoardAPI.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User insertUser(User user) throws SQLException {
        return userRepository.insertUser(user);
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        return userRepository.getAllUsers();
    }

    @Override
    public User getUserByUserId(String userId) throws SQLException {
        return userRepository.getUserByUserId(userId);
    }

    @Override
    public void updateUserName(String userId, User user) throws SQLException { userRepository.updateUserName(userId, user); }

    @Override
    public void updateUserPw(String userId, User user) throws SQLException {
        userRepository.updateUserPw(userId, user);
    }

    @Override
    public void deleteUser(String userId) throws SQLException {
        userRepository.deleteUser(userId);
    }
}
