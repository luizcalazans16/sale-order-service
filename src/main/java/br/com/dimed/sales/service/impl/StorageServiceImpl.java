package br.com.dimed.sales.service.impl;

import br.com.calazans.model.Product;
import br.com.calazans.model.Storage;
import br.com.dimed.sales.dto.constants.StorageUpdateOperationEnum;
import br.com.dimed.sales.repository.StorageRepository;
import br.com.dimed.sales.service.ProductService;
import br.com.dimed.sales.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class StorageServiceImpl implements StorageService {

    @Autowired
    private StorageRepository storageRepository;

    @Autowired
    private ProductService productService;

    @Override
    public Storage findStorageByProductId(final UUID productId) {
        Optional<Storage> storage = storageRepository.findStorageByProductId(productId);
        return storage.orElseThrow();
    }

    @Override
    public Storage registerProductStorage(final UUID productId, final Integer productQuantity) {
        Product foundProduct = productService.findProductById(productId);

        var storage = new Storage();
        storage.setProduct(foundProduct);
        storage.setProductQuantity(productQuantity);
        storage.setLastUpdateAt(LocalDateTime.now());

        storage = storageRepository.save(storage);

        return storage;
    }


    @Override
    public void updateProductStoragePostSale(UUID storedProductId, Integer quantity, StorageUpdateOperationEnum storageUpdateOperationEnum) {
        Storage storage = findStorageByProductId(storedProductId);
        Integer updatedProductQuantity = 0;
        if(storageUpdateOperationEnum.equals(StorageUpdateOperationEnum.SALE_ORDER)) {
            updatedProductQuantity = storage.getProductQuantity() - quantity;
        } else {
            updatedProductQuantity = storage.getProductQuantity() + quantity;
        }

        storage.setProductQuantity(updatedProductQuantity);
        storage.setLastUpdateAt(LocalDateTime.now());

        storageRepository.save(storage);
    }
}
