package by.logonuk.controller.converter;

import by.logonuk.controller.response.ProductResponse;
import by.logonuk.domain.Product;
import com.google.gson.Gson;

import java.sql.Timestamp;

public class ProductConverter implements ConverterInterface<Product, ProductResponse>{

    public Product createRequestToEntity(StringBuilder sb) {
        Gson gson = new Gson();
        Product product = gson.fromJson(String.valueOf(sb), Product.class);
        product.setCreationDate(new Timestamp(new java.util.Date().getTime()));
        product.setModificationDate(new Timestamp(new java.util.Date().getTime()));
        product.setIsDeleted(false);
        return product;
    }

    public Product updateRequestToEntity(StringBuilder sb) {
        Gson gson = new Gson();
        Product product = gson.fromJson(String.valueOf(sb), Product.class);
        product.setModificationDate(new Timestamp(new java.util.Date().getTime()));
        return product;
    }

    public ProductResponse entityToResponse(Product product) {
        ProductResponse productResponse = new ProductResponse();
        productResponse.setId(product.getId());
        productResponse.setProductName(product.getProductName());
        productResponse.setPrice(product.getPrice());
        productResponse.setCreationDate(product.getCreationDate());
        productResponse.setModificationDate(product.getModificationDate());
        productResponse.setIsDeleted(product.getIsDeleted());
        return productResponse;
    }
}
