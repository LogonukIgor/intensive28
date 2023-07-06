package by.logonuk.repository.deal;

import by.logonuk.configuration.DatabaseConnectionConfig;
import by.logonuk.domain.Deal;
import by.logonuk.exception.NoSuchEntityException;

import java.sql.*;

public class DealRepository implements DealRepositoryInterface{
    @Override
    public Deal findById(Integer id) {
        final String findByIdQuery = "select * from shop.deal where id = " + id;

        Connection connection;
        Statement statement;
        ResultSet rs;

        try {
            connection = DatabaseConnectionConfig.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(findByIdQuery);
            boolean hasRow = rs.next();
            if (hasRow) {
                return new DealRowMapper().map(rs);
            } else {
                throw new NoSuchEntityException("Entity User with id " + id + " does not exist", 404);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Deal create(Deal object) {
        final String insertQuery =
                "insert into shop.deal (user_id, product_id, quantity, price) " +
                        " values (?, ?, ?, ?);";

        Connection connection;
        PreparedStatement statement;

        try {
            connection = DatabaseConnectionConfig.getConnection();
            statement = connection.prepareStatement(insertQuery);

            statement.setInt(1, object.getUserId());
            statement.setInt(2, object.getProductId());
            statement.setInt(3, object.getQuantity());
            statement.setDouble(4, object.getPrice());

            statement.executeUpdate();

            ResultSet resultSet = connection.prepareStatement("SELECT currval('shop.order_id_seq') as last_id").executeQuery();
            resultSet.next();
            int dealLastInsertId = resultSet.getInt("last_id");

            return findById(dealLastInsertId);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Deal update(Deal object) {
        final String updateQuery =
                "update shop.deal " +
                        "set " +
                        "user_id = ?, product_id = ?, quantity = ?, price = ?, modification_date = ?" +
                        " where id = ?";

        Connection connection;
        PreparedStatement statement;

        try {
            connection = DatabaseConnectionConfig.getConnection();
            statement = connection.prepareStatement(updateQuery);

            statement.setInt(1, object.getUserId());
            statement.setInt(2, object.getProductId());
            statement.setInt(3, object.getQuantity());
            statement.setDouble(4, object.getPrice());
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
                "delete from shop.deal where id = ?";

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
