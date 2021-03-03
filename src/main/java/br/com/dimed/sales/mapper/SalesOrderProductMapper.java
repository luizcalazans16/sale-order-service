package br.com.dimed.sales.mapper;

import br.com.dimed.sales.dto.SalesOrderProductDto;
import br.com.dimed.sales.model.SalesOrderProduct;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SalesOrderProductMapper {

    public static SalesOrderProduct map(SalesOrderProductDto dto) {
        if (dto == null) {
            return null;
        }
        var entity = new SalesOrderProduct();

        entity.setId(dto.getId());
        entity.setProduct(ProductMapper.map(dto.getProduct()));
        entity.setOrderedQuantity(dto.getOrderedQuantity());

        return entity;
    }

    public static SalesOrderProductDto map(SalesOrderProduct entity) {
        return (entity == null) ? null :
                SalesOrderProductDto.builder()
                .id(entity.getId())
                .product(ProductMapper.map(entity.getProduct()))
                .salesOrderId(entity.getSalesOrder().getId())
                .orderedQuantity(entity.getOrderedQuantity())
                .build();
    }
}
