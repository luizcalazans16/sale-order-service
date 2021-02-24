package br.com.dimed.sales.mapper;

import br.com.dimed.sales.dto.SalesOrderDto;
import br.com.dimed.sales.model.Product;
import br.com.dimed.sales.model.SalesOrder;
import lombok.experimental.UtilityClass;

import java.util.stream.Collectors;

@UtilityClass
public class SalesOrderMapper {

    public static SalesOrderDto map(SalesOrder entity) {
        if (entity == null) {
            return null;
        }
        return SalesOrderDto.builder()
                .id(entity.getId())
                .issueDate(entity.getIssueDate())
                .productList(entity.getProductList()
                        .stream()
                        .map(salesOrderProduct -> SalesOrderProductMapper.map(salesOrderProduct))
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
                .map(salesOrderProduct -> SalesOrderProductMapper.map(salesOrderProduct))
                .collect(Collectors.toList()));
        entity.setOrderStatus(dto.getOrderStatus());

        return entity;
    }

}
