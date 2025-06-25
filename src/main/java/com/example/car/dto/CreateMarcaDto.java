package com.example.car.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateMarcaDto {
    @Schema(description = "Nome da marca", example = "Toyota")
    private String nome;
}


