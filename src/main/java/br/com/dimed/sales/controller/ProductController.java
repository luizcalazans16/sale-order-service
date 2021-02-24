package br.com.dimed.sales.controller;

import br.com.dimed.sales.dto.ProductDto;
import br.com.dimed.sales.mapper.ProductMapper;
import br.com.dimed.sales.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDto> findAll() {
        log.info("Listando os produtos");
        return productService.listProducts()
                .stream()
                .map(ProductMapper::map)
                .collect(Collectors.toList());
    }

    @GetMapping("/{productId}")
    public ProductDto findProductById(@PathVariable final UUID productId) {
        log.info("Buscando produto pelo código: [{}]", productId);
        return ProductMapper.map(productService.findProductById(productId));
    }

    @PostMapping
    public ProductDto register(@RequestBody @Valid ProductDto productDto) {
        log.info("Cadastrando produto: productDto[{}]", productDto);
        var entity = ProductMapper.map(productDto);
        entity = productService.register(entity);
        return ProductMapper.map(entity);
    }
}
