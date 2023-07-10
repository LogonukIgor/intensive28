package by.logonuk.util;

import by.logonuk.domain.Deal;
import by.logonuk.domain.Product;
import by.logonuk.repository.product.ProductRepository;
import by.logonuk.repository.product.ProductRepositoryInterface;
import by.logonuk.repository.user.UserRepository;
import by.logonuk.repository.user.UserRepositoryInterface;

public class DealValidator {

    private UserRepositoryInterface userRepository;

    private ProductRepositoryInterface productRepository;

    public DealValidator() {
        super();
        this.productRepository = new ProductRepository();
        this.userRepository = new UserRepository();
    }

    // If no such entity exists, NoSuchEntityException will be thrown.
    public Double validate(Deal deal) {
        userRepository.findById(deal.getUserId());
        Product product = productRepository.findById(deal.getProductId());
        return product.getPrice();
    }
}
