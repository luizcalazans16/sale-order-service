package br.com.dimed.sales.controller;

import br.com.dimed.sales.dto.StorageDto;
import br.com.dimed.sales.mapper.StorageMapper;
import br.com.dimed.sales.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/storage")
@Slf4j
public class StorageController {

    @Autowired
    private StorageService storageService;

    @PostMapping("/{productId}")
    public StorageDto registerProductStorage(@PathVariable final UUID productId, @RequestBody Integer productQuantity) {
        log.info("Registrando estoque para o produto: [{}], quantidade: [{}]", productId, productQuantity);
        return StorageMapper.map(storageService.registerProductStorage(productId,productQuantity));
    }
}
