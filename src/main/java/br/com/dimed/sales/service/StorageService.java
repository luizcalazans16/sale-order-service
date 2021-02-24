package br.com.dimed.sales.service;

import br.com.dimed.sales.model.Storage;

import java.util.UUID;

public interface StorageService {

    Storage findStorageByProductId(UUID productId);

    Storage registerProductStorage(UUID productId, Integer productQuantity);

    void updateProductStorage(UUID storedProductId, Integer quantity);
}
