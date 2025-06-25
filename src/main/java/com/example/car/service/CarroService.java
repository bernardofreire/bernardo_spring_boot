
package com.example.car.service;

import com.example.car.dto.CreateCarroDto;
import com.example.car.dto.RecoveryCarroDto;
import com.example.car.dto.UpdateCarroDto;
import com.example.car.mapper.CarroMapper;
import com.example.car.entities.Carro;
import com.example.car.entities.Marca;
import com.example.car.repository.CarroRepository;
import com.example.car.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {

    @Autowired
    private CarroRepository carroRepository;

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private CarroMapper carroMapper;

    public RecoveryCarroDto createCarro(CreateCarroDto createCarroDto) {
        Marca marca = marcaRepository.findById(createCarroDto.getMarcaId())
                .orElseThrow(() -> new RuntimeException("Marca não encontrada"));
        Carro carro = carroMapper.toCarro(createCarroDto);
        carro.setMarca(marca);
        return carroMapper.toRecoveryCarroDto(carroRepository.save(carro));
    }

    public List<RecoveryCarroDto> getAllCarros() {
        return carroRepository.findAll().stream()
                .map(carroMapper::toRecoveryCarroDto)
                .collect(Collectors.toList());
    }

    public Optional<RecoveryCarroDto> getCarroById(Long id) {
        return carroRepository.findById(id)
                .map(carroMapper::toRecoveryCarroDto);
    }

    public Optional<RecoveryCarroDto> updateCarro(Long id, UpdateCarroDto updateCarroDto) {
        return carroRepository.findById(id)
                .map(carro -> {
                    carroMapper.updateCarroFromDto(updateCarroDto, carro);
                    Marca marca = marcaRepository.findById(updateCarroDto.getMarcaId())
                            .orElseThrow(() -> new RuntimeException("Marca não encontrada"));
                    carro.setMarca(marca);
                    return carroMapper.toRecoveryCarroDto(carroRepository.save(carro));
                });
    }

    public boolean deleteCarro(Long id) {
        return carroRepository.findById(id)
                .map(carro -> {
                    carroRepository.delete(carro);
                    return true;
                })
                .orElse(false);
    }
}


