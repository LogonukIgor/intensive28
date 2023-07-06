package by.logonuk.controller.converter;

import by.logonuk.controller.response.UserResponse;
import by.logonuk.domain.User;
import com.google.gson.Gson;

import java.sql.Timestamp;

public class UserConverter implements ConverterInterface<User, UserResponse>{

    public User createRequestToEntity(StringBuilder sb) {
        Gson gson = new Gson();
        User user = gson.fromJson(String.valueOf(sb), User.class);
        user.setCreationDate(new Timestamp(new java.util.Date().getTime()));
        user.setModificationDate(new Timestamp(new java.util.Date().getTime()));
        user.setIsDeleted(false);
        return user;
    }

    public User updateRequestToEntity(StringBuilder sb) {
        Gson gson = new Gson();
        User user = gson.fromJson(String.valueOf(sb), User.class);
        user.setModificationDate(new Timestamp(new java.util.Date().getTime()));
        return user;
    }

    public UserResponse entityToResponse(User user) {
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setUserName(user.getUserName());
        userResponse.setSurname(user.getSurname());
        userResponse.setLogin(user.getLogin());
        userResponse.setPassword(user.getPassword());
        userResponse.setCreationDate(user.getCreationDate());
        userResponse.setModificationDate(user.getModificationDate());
        userResponse.setIsDeleted(user.getIsDeleted());
        return userResponse;
    }
}
