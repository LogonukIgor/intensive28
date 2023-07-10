package by.logonuk.repository.product;

import by.logonuk.domain.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.logonuk.repository.product.ProductTableColumns.*;

public class ProductRowMapper {
    public static Product map(ResultSet rs) throws SQLException {

        Product product = new Product();

        product.setId(rs.getInt(ID));
        product.setProductName(rs.getString(NAME));
        product.setPrice(rs.getDouble(PRICE));
        product.setCreationDate(rs.getTimestamp(CREATED));
        product.setModificationDate(rs.getTimestamp(CHANGED));
        product.setIsDeleted(rs.getBoolean(IS_DELETED));

        return product;
    }
}
