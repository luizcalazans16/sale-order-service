package br.com.dimed.sales.service;

import br.com.calazans.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    Product findProductById(UUID productId);

    Product register(Product entity);

    List<Product> listProducts();



}
