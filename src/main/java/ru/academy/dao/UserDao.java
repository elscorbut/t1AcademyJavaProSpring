package ru.academy.dao;

import org.springframework.stereotype.Component;
import ru.academy.domain.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao {

    private final DataSource dataSource;

    private final static String CREATE_SQL = """
            insert into users (username) values(?)
            """;

    private final static String DELETE_SQL = """
            delete from users where id = ?;
            """;

    private final static String UPDATE_USERNAME_BY_ID_SQL = """
            update users set username = ? where id = ?;
            """;

    private final static String FIND_BY_ID_SQL = """
            select * from users where id = ?;
            """;

    private final static String FIND_ALL_SQL = """
            select * from users;
            """;

    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public User create(User user) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getUsername());

            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong("id"));
            }

            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int deleteById(Long userId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setLong(1, userId);

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int updateUsernameById(Long userId, String newUsername) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_USERNAME_BY_ID_SQL)) {
            statement.setString(1, newUsername);
            statement.setLong(2, userId);

            return statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public User findById(Long userId) {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_BY_ID_SQL)) {
            statement.setLong(1, userId);

            User result = null;
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                result = new User(resultSet.getLong("id"), resultSet.getString("username"));
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<User> findAll() {
        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(FIND_ALL_SQL)) {

            List<User> result = new ArrayList<>();
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                result.add(new User(resultSet.getLong("id"), resultSet.getString("username")));
            }

            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
