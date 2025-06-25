package com.example.car.service;

import com.example.car.dto.CreateMarcaDto;
import com.example.car.dto.RecoveryMarcaDto;
import com.example.car.dto.UpdateMarcaDto;
import com.example.car.mapper.MarcaMapper;
import com.example.car.entities.Marca;
import com.example.car.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    @Autowired
    private MarcaMapper marcaMapper;

    public RecoveryMarcaDto createMarca(CreateMarcaDto createMarcaDto) {
        Marca marca = marcaMapper.toMarca(createMarcaDto);
        return marcaMapper.toRecoveryMarcaDto(marcaRepository.save(marca));
    }

    public List<RecoveryMarcaDto> getAllMarcas() {
        return marcaRepository.findAll().stream()
                .map(marcaMapper::toRecoveryMarcaDto)
                .collect(Collectors.toList());
    }

    public Optional<RecoveryMarcaDto> getMarcaById(Long id) {
        return marcaRepository.findById(id)
                .map(marcaMapper::toRecoveryMarcaDto);
    }

    public Optional<RecoveryMarcaDto> updateMarca(Long id, UpdateMarcaDto updateMarcaDto) {
        return marcaRepository.findById(id)
                .map(marca -> {
                    marcaMapper.updateMarcaFromDto(updateMarcaDto, marca);
                    return marcaMapper.toRecoveryMarcaDto(marcaRepository.save(marca));
                });
    }

    public boolean deleteMarca(Long id) {
        return marcaRepository.findById(id)
                .map(marca -> {
                    marcaRepository.delete(marca);
                    return true;
                })
                .orElse(false);
    }
}


