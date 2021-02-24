package br.com.dimed.sales.mapper;

import br.com.dimed.sales.dto.StorageDto;
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
}
