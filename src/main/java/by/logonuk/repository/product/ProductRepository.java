package by.logonuk.repository.product;

import by.logonuk.configuration.DatabaseConnectionConfig;
import by.logonuk.domain.Product;
import by.logonuk.exception.NoSuchEntityException;

import java.sql.*;

public class ProductRepository implements ProductRepositoryInterface{
    @Override
    public Product findById(Integer id) {
        final String findByIdQuery = "select * from shop.goods where id = " + id;

        Connection connection;
        Statement statement;
        ResultSet rs;

        try {
            connection = DatabaseConnectionConfig.getConnection();
            statement = connection.createStatement();
            rs = statement.executeQuery(findByIdQuery);
            boolean hasRow = rs.next();
            if (hasRow) {
                return new ProductRowMapper().map(rs);
            } else {
                throw new NoSuchEntityException("Entity User with id " + id + " does not exist", 404);
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Product create(Product object) {
        final String insertQuery =
                "insert into shop.goods (product_name, price) " +
                        " values (?, ?);";

        Connection connection;
        PreparedStatement statement;

        try {
            connection = DatabaseConnectionConfig.getConnection();
            statement = connection.prepareStatement(insertQuery);

            statement.setString(1, object.getProductName());
            statement.setDouble(2, object.getPrice());

            statement.executeUpdate();

            ResultSet resultSet = connection.prepareStatement("SELECT currval('shop.goods_id_seq') as last_id").executeQuery();
            resultSet.next();
            int productLastInsertId = resultSet.getInt("last_id");

            return findById(productLastInsertId);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new RuntimeException("SQL Issues!");
        }
    }

    @Override
    public Product update(Product object) {
        final String updateQuery =
                "update shop.goods " +
                        "set " +
                        "product_name = ?, price = ?, modification_date = ?" +
                        " where id = ?";

        Connection connection;
        PreparedStatement statement;

        try {
            connection = DatabaseConnectionConfig.getConnection();
            statement = connection.prepareStatement(updateQuery);

            statement.setString(1, object.getProductName());
            statement.setDouble(2, object.getPrice());
            statement.setTimestamp(3, object.getModificationDate());
            statement.setInt(4, object.getId());

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
                "delete from shop.goods where id = ?";

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
