package com.paymentanalytics.jump2digital.controllers;

import com.paymentanalytics.jump2digital.dtos.ProductDto;
import com.paymentanalytics.jump2digital.exceptions.ArgumentNotValidException;
import com.paymentanalytics.jump2digital.exceptions.InvalidTypeException;
import com.paymentanalytics.jump2digital.services.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    IProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        try {
            return productService.createProduct(productDto);
        } catch (ArgumentNotValidException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The product cannot be persisted.");
        } catch (InvalidTypeException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The product type is not correct.");
        }
    }

    @GetMapping
    @ResponseStatus(HttpStatus.FOUND)
    public ProductDto getProduct(@RequestBody UUID id) {
        try {
            return productService.getProductDtoById(id);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with ID " + id + " does not exist.");
        }
    }


    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        try {
            return productService.updateProduct(productDto);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with ID " + productDto.getId() + " does not exist.");
        } catch (ArgumentNotValidException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The product cannot be persisted.");
        } catch (InvalidTypeException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "The product type is not correct.");
        }
    }


    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@RequestBody UUID id) {
        try {
            productService.deleteProduct(id);
        } catch (EntityNotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product with ID " + id + " does not exist.");
        }
    }

}
