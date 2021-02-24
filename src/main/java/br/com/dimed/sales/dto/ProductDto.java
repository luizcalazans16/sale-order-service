package br.com.dimed.sales.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.UUID;

@Value
@Builder
@JsonDeserialize(builder = ProductDto.ProductDtoBuilder.class)
public class ProductDto {

    @Null
    private final UUID id;

    @NotNull
    private final String name;

    private final String description;

    @NotNull
    private final BigDecimal defaultPrice;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class ProductDtoBuilder {

    }
}
