package by.logonuk.repository.user;

import by.logonuk.configuration.DatabaseConnectionConfig;
import by.logonuk.domain.User;
import by.logonuk.exception.NoSuchEntityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepository implements UserRepositoryInterface {

    @Override
    public User findById(Integer id) {
        final String findByIdQuery = "select * from shop.users where id = " + id;

        Connection connection;
        Statement statement;
        ResultSet rs;

        try {
            connection = DatabaseConnectionConfig.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(findByIdQuery);
            boolean hasRow = rs.next();
            if (hasRow) {
                return new UserRowMapper().map(rs);
            } else {
                throw new NoSuchEntityException("Entity User with id " + id + " does not exist", 404);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public User create(User object) {
        final String insertQuery =
                "insert into shop.users (user_name, surname, login, password) " +
                        " values (?, ?, ?, ?);";

        Connection connection;
        PreparedStatement statement;

        try {
            connection = DatabaseConnectionConfig.getConnection();
            statement = connection.prepareStatement(insertQuery);

            statement.setString(1, object.getUserName());
            statement.setString(2, object.getSurname());
            statement.setString(3, object.getLogin());
            statement.setString(4, object.getPassword());

            statement.executeUpdate();

            ResultSet resultSet = connection.prepareStatement("SELECT currval('shop.users_id_seq') as last_id").executeQuery();
            resultSet.next();
            int userLastInsertId = resultSet.getInt("last_id");

            return findById(userLastInsertId);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public User update(User object) {
        final String updateQuery =
                "update shop.users " +
                        "set " +
                        "user_name = ?, surname = ?, login = ?, password = ?, modification_date = ?" +
                        " where id = ?";

        Connection connection;
        PreparedStatement statement;

        try {
            connection = DatabaseConnectionConfig.getConnection();
            statement = connection.prepareStatement(updateQuery);

            statement.setString(1, object.getUserName());
            statement.setString(2, object.getSurname());
            statement.setString(3, object.getLogin());
            statement.setString(4, object.getPassword());
            statement.setTimestamp(5, object.getModificationDate());
            statement.setInt(6, object.getId());

            statement.executeUpdate();

            return findById(object.getId());
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Integer delete(Integer id) {
        final String deleteQuery =
                "delete from shop.users where id = ?";

        Connection connection;
        PreparedStatement statement;

        try {
            connection = DatabaseConnectionConfig.getConnection();
            statement = connection.prepareStatement(deleteQuery);
            statement.setLong(1, id);
            statement.executeUpdate();

            return id;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }
}
