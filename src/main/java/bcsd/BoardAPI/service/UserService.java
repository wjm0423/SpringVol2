package bcsd.BoardAPI.service;

import bcsd.BoardAPI.domain.User;
import java.util.List;

public interface UserService {
    public User insertUser(User user);
    public List<User> getAllUsers();
    public User getUserByUserId(String userId);
    public void updateUserName(String userId, String user);
    public void updateUserPw(String userId, String user);
    public void deleteUser(String userId);
}
