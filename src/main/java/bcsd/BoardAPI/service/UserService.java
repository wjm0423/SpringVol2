package bcsd.BoardAPI.service;

import bcsd.BoardAPI.domain.User;
import bcsd.BoardAPI.repository.UserRepository;
import java.sql.SQLException;
import java.util.List;

public interface UserService {
    public User insertUser(User user) throws SQLException;
    public List<User> getAllUsers() throws SQLException;
    public User getUserByUserId(String userId) throws SQLException;
    public void updateUserName(String userId, User user) throws SQLException;
    public void updateUserPw(String userId, User user) throws SQLException;
    public void deleteUser(String userId) throws SQLException;
}
