package com.paymentanalytics.jump2digital.services;

import com.paymentanalytics.jump2digital.dtos.ProductDto;
import com.paymentanalytics.jump2digital.model.entities.Product;

import java.util.UUID;

public interface IProductService {

    ProductDto createProduct(ProductDto productDto);

    ProductDto mapProductToDto(Product product);

    Product getProductById(UUID id);

    ProductDto getProductDtoById(UUID id);

    ProductDto updateProduct(ProductDto productDto);

    void deleteProduct(UUID id);
}
