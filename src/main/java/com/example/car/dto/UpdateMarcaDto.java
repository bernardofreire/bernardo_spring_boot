
package com.example.car.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateMarcaDto {
    @Schema(description = "Novo nome da marca", example = "Volkswagen")
    private String nome;
}


