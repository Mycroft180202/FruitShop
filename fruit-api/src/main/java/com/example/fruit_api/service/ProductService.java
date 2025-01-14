package com.example.fruit_api.service;

import com.example.fruit_api.dto.ProductDto;
import com.example.fruit_api.common.api.exception.ResourceNotFoundException;
import com.example.fruit_api.models.Category;
import com.example.fruit_api.models.Product;
import com.example.fruit_api.repository.CategoryRepository;
import com.example.fruit_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<ProductDto> findAllProducts() {
        return productRepository.findAll().stream()
                .map(ProductDto::new)
                .collect(Collectors.toList());
    }

    public Product findProductById(Long id) {
        return productRepository.findByIdWithCategory(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + id));
    }

    public Product addProduct(Product product) {
        Category category = categoryRepository.findById(product.getCategory().getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("Category not found with id: " + product.getCategory().getCategoryId()));
        product.setCategory(category);
        return productRepository.save(product);
    }

    public Product updateProduct(Long id, Product productDetails) {
        Optional<Product> optionalProduct = productRepository.findByIdWithCategory(id);
        if (!optionalProduct.isPresent()) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        Product product = optionalProduct.get();
        product.setProductName(productDetails.getProductName());
        product.setDescription(productDetails.getDescription());
        product.setPrice(productDetails.getPrice());
        product.setQuantityInStock(productDetails.getQuantityInStock());
        product.setImageURL(productDetails.getImageURL());

        // Lấy category từ database dựa trên categoryId
        Optional<Category> optionalCategory = categoryRepository.findById(productDetails.getCategory().getCategoryId());
        if (!optionalCategory.isPresent()) {
            throw new ResourceNotFoundException("Category not found with id: " + productDetails.getCategory().getCategoryId());
        }
        Category category = optionalCategory.get();
        product.setCategory(category);

        return productRepository.save(product);
    }


    public void deleteProduct(Long id) {
        Product product = findProductById(id);
        productRepository.delete(product);
    }
}