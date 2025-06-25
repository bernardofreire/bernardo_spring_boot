package com.example.car.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RecoveryCarroDto {
    @Schema(description = "ID do carro", example = "10")
    private Long id;

    @Schema(description = "Modelo do carro", example = "Yaris")
    private String modelo;

    @Schema(description = "Ano de fabricação", example = "2021")
    private Integer ano;

    @Schema(description = "ID da marca associada", example = "1")
    private Long marcaId;
}


