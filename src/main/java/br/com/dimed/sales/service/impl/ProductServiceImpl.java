package br.com.dimed.sales.service.impl;

import br.com.dimed.sales.exceptions.ResourceNotFoundException;
import br.com.dimed.sales.model.Product;
import br.com.dimed.sales.repository.ProductRepository;
import br.com.dimed.sales.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> listProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product findProductById(UUID productId) {
        Optional<Product> product = productRepository.findById(productId);
        return product.orElseThrow(() -> new ResourceNotFoundException(Product.class, productId));
    }

    @Override
    public Product register(Product entity) {
        return store(null, entity);
    }

    private Product store(UUID productId, Product entity) {
        entity.setId(productId);

        return productRepository.save(entity);
    }
}
