package br.com.dimed.sales.mapper;

import br.com.calazans.model.SalesOrder;
import br.com.dimed.sales.dto.SalesOrderDto;
import lombok.experimental.UtilityClass;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.stream.Collectors;

@UtilityClass
public class SalesOrderMapper {

    @Valid
    public static SalesOrderDto map(SalesOrder entity) {
        if (entity == null) {
            return null;
        }
        return SalesOrderDto.builder()
                .id(entity.getId())
                .issueDate(entity.getIssueDate())
                .productList(entity.getProductList()
                        .stream()
                        .map((@NotNull var salesOrderProduct) ->
                                SalesOrderProductMapper.map(salesOrderProduct))
                        .collect(Collectors.toList()))
                .price(entity.getPrice())
                .orderStatus(entity.getOrderStatus())
                .build();
    }

    public static SalesOrder map(SalesOrderDto dto) {
        if (dto == null) {
            return null;
        }
        SalesOrder entity = new SalesOrder();
        entity.setId(dto.getId());
        entity.setIssueDate(dto.getIssueDate());
        entity.setPrice(dto.getPrice());
        entity.setProductList(dto.getProductList()
                .stream()
                .map(SalesOrderProductMapper::map)
                .collect(Collectors.toList()));
        entity.setOrderStatus(dto.getOrderStatus());

        return entity;
    }

}
