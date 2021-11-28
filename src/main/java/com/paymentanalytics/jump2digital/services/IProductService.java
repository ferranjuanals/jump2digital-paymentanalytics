package com.paymentanalytics.jump2digital.services;

import com.paymentanalytics.jump2digital.dtos.ProductDto;
import com.paymentanalytics.jump2digital.model.entities.Product;

public interface IProductService {

    ProductDto createProduct(ProductDto productDto);

    ProductDto mapProductToDto(Product product);

    Product getProductById(String id);

}
