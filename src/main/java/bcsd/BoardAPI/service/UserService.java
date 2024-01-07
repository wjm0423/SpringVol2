package bcsd.BoardAPI.service;

import bcsd.BoardAPI.domain.JWT;
import bcsd.BoardAPI.domain.User;
import jakarta.transaction.Transactional;

import java.util.List;

public interface UserService {
    @Transactional
    JWT signIn(String userName, String userPw);

    public User insertUser(User user);
    public List<User> getAllUsers();
    public User getUserByUserId(String userId);
    public void updateUserName(String userId, String user);
    public void updateUserPw(String userId, String user);
    public void deleteUser(String userId);
}
