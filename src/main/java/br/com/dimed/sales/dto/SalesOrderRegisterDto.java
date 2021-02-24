package br.com.dimed.sales.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@Builder
@JsonDeserialize(builder = SalesOrderRegisterDto.SalesOrderRegisterDtoBuilder.class)
public class SalesOrderRegisterDto {

    private List<UUID> productList;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class SalesOrderRegisterDtoBuilder {

    }
}
