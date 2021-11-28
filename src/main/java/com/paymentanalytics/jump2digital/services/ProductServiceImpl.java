package com.paymentanalytics.jump2digital.services;

import com.paymentanalytics.jump2digital.dtos.ProductDto;
import com.paymentanalytics.jump2digital.exceptions.ArgumentNotValidException;
import com.paymentanalytics.jump2digital.model.entities.Product;
import com.paymentanalytics.jump2digital.model.valueobjects.ProductType;
import com.paymentanalytics.jump2digital.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductDto createProduct(ProductDto productDto) {
        Product product = mapProduct(productDto);
        productRepository.save(product);
        return mapDto(product);
    }

    @Override
    public ProductDto mapProductToDto(Product product) {
        return mapDto(product);
    }

    @Override
    public Product getProductById(String id) {
        return productRepository.getById(UUID.fromString(id));
    }

    @Override
    public ProductDto getProductDtoById(String id) {
        Product product = productRepository.getById(UUID.fromString(id));
        return mapDto(product);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto) {
        Product product = productRepository.getById(UUID.fromString(productDto.getId()));
        if(productDto.getName() != null) product.setName(productDto.getName());
        if(productDto.getPrice() != null) product.setPrice(productDto.getPrice());
        if(productDto.getProductType() != null) product.setProductType(ProductType.getType(productDto.getProductType()));
        if(productDto.getDescription() != null) product.setDescription(productDto.getDescription());
        validate(product);
        productRepository.save(product);
        return mapDto(product);
    }

    @Override
    public void deleteProduct(String id) {
        Product product = productRepository.getById(UUID.fromString(id));
        productRepository.delete(product);
    }

    private ProductDto mapDto(Product product) {
        return ProductDto.builder()
                .id(product.getId().toString())
                .name(product.getName())
                .price((Double) product.getPrice())
                .productType(product.getProductType().toString())
                .description(product.getDescription())
                .build();
    }

    private Product mapProduct(ProductDto productDto) {
        Product product = Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .productType(ProductType.getType(productDto.getProductType()))
                .description(productDto.getDescription())
                .build();
        validate(product);
        return product;
    }

    private void validate(Product product) {
        if(product.getName() == null || product.getName().isBlank())
            throw new ArgumentNotValidException("The product must have a valid name");
        if(productRepository.existsByName(product.getName()))
            throw new ArgumentNotValidException("A product with this name already exists.");
        if(product.getPrice() == null || product.getPrice().doubleValue() <= 0)
            throw new ArgumentNotValidException("The product must have a positive value as a price");
        if(product.getDescription() == null || product.getDescription().isBlank())
            throw new ArgumentNotValidException("The product must have a valid description");
    }

}
