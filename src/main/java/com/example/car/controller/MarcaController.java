package com.example.car.controller;

import com.example.car.dto.CreateMarcaDto;
import com.example.car.dto.RecoveryMarcaDto;
import com.example.car.dto.UpdateMarcaDto;
import com.example.car.service.MarcaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marcas")
@Tag(name = "Marcas", description = "Operações relacionadas às marcas de carros")
public class MarcaController {

    @Autowired
    private MarcaService marcaService;

    @PostMapping
    @Operation(
            summary = "Cadastrar uma nova marca",
            description = "Cria um novo registro de marca no sistema. A marca será identificada automaticamente por um ID gerado pelo banco de dados."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca criada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public RecoveryMarcaDto createMarca(@RequestBody CreateMarcaDto createMarcaDto) {
        return marcaService.createMarca(createMarcaDto);
    }

    @GetMapping
    @Operation(
            summary = "Listar todas as marcas",
            description = "Retorna uma lista de todas as marcas de veículos cadastradas no sistema, incluindo os carros associados a cada uma delas."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marcas retornadas com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro interno ao buscar marcas")
    })
    public List<RecoveryMarcaDto> getAllMarcas() {
        return marcaService.getAllMarcas();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar uma marca pelo ID",
            description = "Busca e retorna uma marca específica com base no seu ID. Também retorna os carros associados à marca, se houver."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca encontrada"),
            @ApiResponse(responseCode = "404", description = "Marca não encontrada")
    })
    public ResponseEntity<RecoveryMarcaDto> getMarcaById(@PathVariable Long id) {
        return marcaService.getMarcaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar dados de uma marca existente",
            description = "Atualiza o nome de uma marca já cadastrada com base no ID informado. Retorna erro 404 se a marca não existir."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca atualizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Marca não encontrada"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<RecoveryMarcaDto> updateMarca(@PathVariable Long id, @RequestBody UpdateMarcaDto updateMarcaDto) {
        return marcaService.updateMarca(id, updateMarcaDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Excluir uma marca pelo ID",
            description = "Remove permanentemente uma marca com base no seu ID. Caso a marca esteja associada a algum carro, é necessário garantir a integridade referencial."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Marca excluída com sucesso"),
            @ApiResponse(responseCode = "404", description = "Marca não encontrada")
    })
    public ResponseEntity<Void> deleteMarca(@PathVariable Long id) {
        if (marcaService.deleteMarca(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


