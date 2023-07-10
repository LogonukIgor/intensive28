package by.logonuk.service;

import by.logonuk.controller.converter.ConverterInterface;
import by.logonuk.controller.converter.UserConverter;
import by.logonuk.controller.response.UserResponse;
import by.logonuk.domain.User;
import by.logonuk.repository.user.UserRepository;
import by.logonuk.repository.user.UserRepositoryInterface;
import by.logonuk.util.RequestBodyReader;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class UserService implements ServiceInterface<Integer, UserResponse>{

    private UserRepositoryInterface repository;

    private ConverterInterface<User, UserResponse> converter;

    public UserService() {
        super();
        this.repository = new UserRepository();
        this.converter = new UserConverter();
    }

    @Override
    public UserResponse create(HttpServletRequest request) {
        StringBuilder sb;
        try {
            sb = RequestBodyReader.readBody(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        User user = converter.createRequestToEntity(sb);
        return converter.entityToResponse(repository.create(user));
    }

    @Override
    public UserResponse update(HttpServletRequest request) {
        StringBuilder sb;
        try {
            sb = RequestBodyReader.readBody(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        User user = converter.updateRequestToEntity(sb);
        return converter.entityToResponse(repository.update(user));
    }

    @Override
    public Integer delete(Integer userId) {
        return repository.delete(userId);
    }

    @Override
    public UserResponse findById(Integer userId) {
        User user = repository.findById(userId);
        return converter.entityToResponse(user);
    }
}
