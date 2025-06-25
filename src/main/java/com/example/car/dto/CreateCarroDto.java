
package com.example.car.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateCarroDto {
    @Schema(description = "Modelo do carro", example = "Corolla")
    private String modelo;

    @Schema(description = "Ano de fabricação", example = "2022")
    private Integer ano;

    @Schema(description = "ID da marca relacionada", example = "1")
    private Long marcaId;
}


