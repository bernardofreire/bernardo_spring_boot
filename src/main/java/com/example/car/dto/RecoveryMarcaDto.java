package com.example.car.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.util.List;

@Data
public class RecoveryMarcaDto {
    @Schema(description = "ID único da marca", example = "1")
    private Long id;

    @Schema(description = "Nome da marca", example = "Toyota")
    private String nome;

    @Schema(description = "Lista de carros vinculados a esta marca")
    private List<RecoveryCarroDto> carros;
}


