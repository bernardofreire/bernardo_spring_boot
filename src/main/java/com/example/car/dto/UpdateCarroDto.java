
package com.example.car.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateCarroDto {
    @Schema(description = "Novo modelo do carro", example = "Corolla Altis")
    private String modelo;

    @Schema(description = "Novo ano do carro", example = "2023")
    private Integer ano;

    @Schema(description = "Novo ID da marca associada", example = "2")
    private Long marcaId;
}


