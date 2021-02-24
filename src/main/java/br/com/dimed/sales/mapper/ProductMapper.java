package br.com.dimed.sales.mapper;

import br.com.dimed.sales.dto.ProductDto;
import br.com.dimed.sales.model.Product;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductMapper {

    public static ProductDto map(Product entity) {
        return (entity == null) ? null :
                ProductDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .defaultPrice(entity.getDefaultPrice())
                .build();
    }

    public static Product map(ProductDto dto) {
        if(dto == null) {
            return null;
        }
        var entity = new Product();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setDefaultPrice(dto.getDefaultPrice());

        return entity;
    }
}
