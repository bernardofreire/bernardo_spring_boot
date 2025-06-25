package com.example.car.controller;

import com.example.car.dto.CreateCarroDto;
import com.example.car.dto.RecoveryCarroDto;
import com.example.car.dto.UpdateCarroDto;
import com.example.car.service.CarroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carros")
@Tag(name = "Carros", description = "Operações relacionadas a veículos")
public class CarroController {

    @Autowired
    private CarroService carroService;

    @PostMapping
    @Operation(
            summary = "Cadastrar um novo carro",
            description = "Cria um novo registro de carro e o associa a uma marca existente via marcaId. O campo 'marcaId' deve referenciar uma marca já cadastrada."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carro cadastrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public RecoveryCarroDto createCarro(@RequestBody CreateCarroDto createCarroDto) {
        return carroService.createCarro(createCarroDto);
    }

    @GetMapping
    @Operation(
            summary = "Listar todos os carros",
            description = "Retorna uma lista com todos os carros cadastrados no sistema, com seus respectivos dados e IDs de marca associados."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de carros retornada com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao buscar os carros")
    })
    public List<RecoveryCarroDto> getAllCarros() {
        return carroService.getAllCarros();
    }

    @GetMapping("/{id}")
    @Operation(
            summary = "Buscar um carro por ID",
            description = "Recupera um carro específico a partir do seu identificador único. Retorna erro 404 se o carro não for encontrado."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carro encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Carro não encontrado")
    })
    public ResponseEntity<RecoveryCarroDto> getCarroById(@PathVariable Long id) {
        return carroService.getCarroById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(
            summary = "Atualizar os dados de um carro",
            description = "Atualiza os dados de um carro existente com base no seu ID. É possível atualizar o modelo, o ano e a marca associada (marcaId)."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carro atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Carro não encontrado"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    public ResponseEntity<RecoveryCarroDto> updateCarro(@PathVariable Long id, @RequestBody UpdateCarroDto updateCarroDto) {
        return carroService.updateCarro(id, updateCarroDto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(
            summary = "Excluir um carro por ID",
            description = "Remove um carro do sistema com base no seu ID. Retorna erro 404 se o carro não existir."
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Carro excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Carro não encontrado")
    })
    public ResponseEntity<Void> deleteCarro(@PathVariable Long id) {
        if (carroService.deleteCarro(id)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


