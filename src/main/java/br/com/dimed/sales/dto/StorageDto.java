package br.com.dimed.sales.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.UUID;

@Value
@Builder
@JsonDeserialize(builder = StorageDto.StorageDtoBuilder.class)
public class StorageDto {

    private final UUID id;

    private final UUID productId;

    private final Integer productQuantity;

    private final LocalDateTime lastUpdateAt;

    @JsonPOJOBuilder(withPrefix = "")
    public static final class StorageDtoBuilder {

    }
}
