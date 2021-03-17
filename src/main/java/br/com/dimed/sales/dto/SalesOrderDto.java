package br.com.dimed.sales.dto;

import br.com.calazans.constants.SalesOrderStatusEnum;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Value
@Builder
@JsonDeserialize(builder = SalesOrderDto.SalesOrderDtoBuilder.class)
public class SalesOrderDto {

    @Null
    private final UUID id;
    @Null
    private final LocalDate issueDate;
    private final BigDecimal price;
    private final List<SalesOrderProductDto> productList;
    private final SalesOrderStatusEnum orderStatus;


    @JsonPOJOBuilder(withPrefix = "")
    public static final class SalesOrderDtoBuilder {

    }
}
