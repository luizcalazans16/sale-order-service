package br.com.dimed.sales.controller;

import br.com.dimed.sales.dto.constants.StorageUpdateOperationEnum;
import br.com.dimed.sales.dto.StorageDto;
import br.com.dimed.sales.mapper.StorageMapper;
import br.com.dimed.sales.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/storage")
@Slf4j
public class StorageController {

    @Autowired
    private StorageService storageService;

    @GetMapping("/{productId}")
    public StorageDto findStorageByProductId(@PathVariable final UUID productId) {
        log.info("Buscando estoque do produto: [{}]", productId);
        return StorageMapper.map(storageService.findStorageByProductId(productId));
    }

    @PostMapping("/{productId}")
    public StorageDto registerProductStorage(@PathVariable final UUID productId, @RequestParam Integer productQuantity) {
        log.info("Registrando estoque para o produto: [{}], quantidade: [{}]", productId, productQuantity);
        return StorageMapper.map(storageService.registerProductStorage(productId, productQuantity));
    }

    @PutMapping("/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateProductStorage(@PathVariable final UUID productId, @RequestParam Integer productQuantity) {
        log.info("Atualizando estoque do produto: [{}], quantidade: [{}]", productId, productQuantity);
        storageService.updateProductStoragePostSale(productId, productQuantity,
                StorageUpdateOperationEnum.ADD_PRODUCT_STORAGE);
    }
}
