package by.logonuk.service;

import by.logonuk.controller.converter.ConverterInterface;
import by.logonuk.controller.converter.ProductConverter;
import by.logonuk.controller.response.ProductResponse;
import by.logonuk.domain.Product;
import by.logonuk.repository.product.ProductRepository;
import by.logonuk.repository.product.ProductRepositoryInterface;
import by.logonuk.util.RequestBodyReader;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;

public class ProductService implements ServiceInterface<Integer, ProductResponse> {

    private ProductRepositoryInterface repository;

    private ConverterInterface<Product, ProductResponse> converter;

    public ProductService() {
        super();
        this.repository = new ProductRepository();
        this.converter = new ProductConverter();
    }
    @Override
    public ProductResponse create(HttpServletRequest request) {
        StringBuilder sb;
        try {
            sb = RequestBodyReader.readBody(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Product product = converter.createRequestToEntity(sb);
        return converter.entityToResponse(repository.create(product));
    }

    @Override
    public ProductResponse update(HttpServletRequest request) {
        StringBuilder sb;
        try {
            sb = RequestBodyReader.readBody(request);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Product product = converter.updateRequestToEntity(sb);
        return converter.entityToResponse(repository.update(product));
    }

    @Override
    public Integer delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public ProductResponse findById(Integer id) {
        Product product = repository.findById(id);
        return converter.entityToResponse(product);
    }
}
