package by.logonuk.repository.deal;

import by.logonuk.domain.Deal;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.logonuk.repository.deal.DealTableColumns.*;

public class DealRowMapper {

    public static Deal map(ResultSet rs) throws SQLException {

        Deal deal = new Deal();

        deal.setId(rs.getInt(ID));
        deal.setUserId(rs.getInt(USER_ID));
        deal.setProductId(rs.getInt(PRODUCT_ID));
        deal.setQuantity(rs.getInt(QUANTITY));
        deal.setPrice(rs.getDouble(PRICE));
        deal.setCreationDate(rs.getTimestamp(CREATED));
        deal.setModificationDate(rs.getTimestamp(CHANGED));
        deal.setIsDeleted(rs.getBoolean(IS_DELETED));

        return deal;
    }
}
