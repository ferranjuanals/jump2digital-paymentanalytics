package com.paymentanalytics.jump2digital.services;

import com.paymentanalytics.jump2digital.dtos.ProductDto;
import com.paymentanalytics.jump2digital.exceptions.EntityNotFoundException;
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
    public Product getProductById(String id) throws EntityNotFoundException {
        return productRepository.findById(UUID.fromString(id)).orElseThrow(
                () -> new EntityNotFoundException("Product not found. The id " + id + " does not exist"));
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
        return Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .productType(ProductType.getType(productDto.getProductType()))
                .description(productDto.getDescription())
                .build();
    }

}
