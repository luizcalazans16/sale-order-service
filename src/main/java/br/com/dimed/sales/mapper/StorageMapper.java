package br.com.dimed.sales.mapper;

import br.com.dimed.sales.dto.StorageDto;
import br.com.dimed.sales.model.Product;
import br.com.dimed.sales.model.Storage;
import jdk.jfr.Unsigned;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StorageMapper {

    public static StorageDto map(Storage entity) {
        return (entity == null) ? null :
                StorageDto.builder()
                .id(entity.getId())
                .productId(entity.getProduct().getId())
                .productQuantity(entity.getProductQuantity())
                .lastUpdateAt(entity.getLastUpdateAt())
                .build();

    }

    public static Storage map(StorageDto dto) {
        if(dto == null) {
            return null;
        }
        var entity = new Storage();

        entity.setId(dto.getId());
        var product = new Product();
        product.setId(dto.getProductId());
        entity.setProduct(product);
        entity.setProductQuantity(dto.getProductQuantity());
        entity.setLastUpdateAt(dto.getLastUpdateAt());

        return entity;
    }
}
