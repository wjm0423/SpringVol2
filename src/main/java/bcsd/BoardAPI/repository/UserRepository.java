package bcsd.BoardAPI.repository;

import bcsd.BoardAPI.DBConnection.DBConnectionManager;
import bcsd.BoardAPI.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class UserRepository {
    public User insertUser(User user) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "insert into users(userId, userName, userPw) values(?, ?, ?)";

        try {
            connection = DBConnectionManager.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, user.getUserId());
            statement.setString(2, user.getUserName());
            statement.setString(3, user.getUserPw());

            statement.executeUpdate();
            return user;
        } catch (SQLException e) {
            log.error("insertUser error={}", e);
            throw e;
        } finally {
            closeResource(connection, statement, null);
        }
    }

    public ArrayList<User> getAllUsers() throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sql = "select * from users";

        try {
            connection = DBConnectionManager.getConnection();
            statement = connection.prepareStatement(sql);
            rs = statement.executeQuery();
            ArrayList<User> users = new ArrayList<>();

            while(rs.next()) {
                users.add(new User(rs.getString("userId"), rs.getString("userName"), rs.getString("userPw")));
            }
            return users;
        } catch (SQLException e) {
            log.error("selectUser error={}", e);
            throw e;
        } finally {
            closeResource(connection, statement, rs);
        }
    }

    public User getUserByUserId(String userId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String sql = "select * from users where userId = ?";

        try {
            connection = DBConnectionManager.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, userId);

            rs = statement.executeQuery();
            User user = new User();
            while (rs.next()) {
                user.setUserId(rs.getString("userId"));
                user.setUserName(rs.getString("userName"));
                user.setUserPw(rs.getString("userPw"));
            }
            return user;
        } catch (SQLException e) {
            log.error("selectUser error={}", e);
            throw e;
        } finally {
            closeResource(connection, statement, rs);
        }
    }

    public void updateUserName(String userId, User user) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "update users set userName = ? where userId = ?";

        try {
            connection = DBConnectionManager.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, user.getUserName());
            statement.setString(2, user.getUserId());

            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("updateUser error={}", e);
            throw e;
        } finally {
            closeResource(connection, statement, null);
        }
    }

    public void updateUserPw(String userId, User user) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "update users set userPw = ? where userId = ?";

        try {
            connection = DBConnectionManager.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, user.getUserPw());
            statement.setString(2, user.getUserId());

            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("updateUser error={}", e);
            throw e;
        } finally {
            closeResource(connection, statement, null);
        }
    }

    public void deleteUser(String userId) throws SQLException {
        Connection connection = null;
        PreparedStatement statement = null;
        String sql = "delete from users where userId = ?";

        try {
            connection = DBConnectionManager.getConnection();
            statement = connection.prepareStatement(sql);

            statement.setString(1, userId);

            statement.executeUpdate();
        } catch (SQLException e) {
            log.error("deleteUser error={}", e);
            throw e;
        } finally {
            closeResource(connection, statement, null);
        }
    }

    private void closeResource(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (Exception e) {
                log.error("error", e);
            }
        }

        if (statement != null) {
            try {
                statement.close();
            } catch (Exception e) {
                log.error("error", e);
            }
        }

        if (connection != null) {
            try {
                connection.close();
            } catch (Exception e) {
                log.error("error", e);
            }
        }
    }
}
