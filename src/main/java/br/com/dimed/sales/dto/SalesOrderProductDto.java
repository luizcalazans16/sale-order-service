package br.com.dimed.sales.dto;

import br.com.dimed.sales.model.Product;
import br.com.dimed.sales.model.SalesOrder;
import br.com.dimed.sales.model.SalesOrderProduct;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import javax.persistence.*;
import java.util.UUID;

@Value
@Builder
@JsonDeserialize(builder = SalesOrderProductDto.SalesOrderProductDtoBuilder.class)
public class SalesOrderProductDto {

    private UUID id;

    private ProductDto product;

    private UUID salesOrderId;

    private Integer orderedQuantity;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class SalesOrderProductDtoBuilder {

    }
}
