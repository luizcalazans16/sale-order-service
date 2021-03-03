package br.com.dimed.sales.service;

import br.com.dimed.sales.dto.constants.StorageUpdateOperationEnum;
import br.com.dimed.sales.model.Storage;

import java.util.UUID;

public interface StorageService {

    Storage findStorageByProductId(UUID productId);

    Storage registerProductStorage(UUID productId, Integer productQuantity);

    void updateProductStoragePostSale(UUID storedProductId, Integer quantity,
                                      StorageUpdateOperationEnum storageUpdateOperationEnum);

}


