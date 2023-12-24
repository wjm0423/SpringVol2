package bcsd.BoardAPI.DBConnection;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static bcsd.BoardAPI.DBConnection.DBConnectionConstant.*;

@Slf4j
public class DBConnectionManager {
    public static Connection getConnection() throws SQLException {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            log.info("connection={}", connection);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
