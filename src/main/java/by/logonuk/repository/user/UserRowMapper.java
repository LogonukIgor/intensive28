package by.logonuk.repository.user;

import by.logonuk.domain.User;

import java.sql.ResultSet;
import java.sql.SQLException;

import static by.logonuk.repository.user.UserTableColumns.*;

public class UserRowMapper {

    public User map(ResultSet rs) throws SQLException {

        User user = new User();

        user.setId(rs.getInt(ID));
        user.setUserName(rs.getString(NAME));
        user.setSurname(rs.getString(SURNAME));
        user.setLogin(rs.getString(LOGIN));
        user.setPassword(rs.getString(PASSWORD));
        user.setCreationDate(rs.getTimestamp(CREATED));
        user.setModificationDate(rs.getTimestamp(CHANGED));
        user.setIsDeleted(rs.getBoolean(IS_DELETED));

        return user;
    }
}
