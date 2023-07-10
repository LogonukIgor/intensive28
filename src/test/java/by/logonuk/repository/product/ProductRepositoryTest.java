package by.logonuk.repository.product;

import by.logonuk.domain.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {

    private ProductRepositoryInterface repository;

    @BeforeEach
    public void setRepository() {
        repository = new ProductRepository();
    }

    @Test
    void createAndFindById_success() {

        // given
        Product testProduct = new Product();

        testProduct.setProductName("NNN");
        testProduct.setPrice(100.5);

        Product product = repository.create(testProduct);

        //when
        assertEquals(testProduct.getProductName(), product.getProductName());
        assertEquals(Integer.class, product.getId().getClass());

        repository.delete(product.getId());
    }

    @Test
    void update_success() {

        //given
        Product testProduct = new Product();

        testProduct.setProductName("NNN");
        testProduct.setPrice(100.5);

        Product product = repository.create(testProduct);

        testProduct.setId(product.getId());
        testProduct.setPrice(120.5);
        testProduct.setModificationDate(new Timestamp(new Date().getTime()));

        //when
        assertEquals(testProduct.getPrice(), repository.update(testProduct).getPrice());

        repository.delete(product.getId());
    }

    @Test
    void delete_success() {
        //given
        Product testProduct = new Product();

        testProduct.setProductName("NNN");
        testProduct.setPrice(100.5);

        Product product = repository.create(testProduct);

        //when
        assertEquals(product.getId(), repository.delete(product.getId()));
    }
}