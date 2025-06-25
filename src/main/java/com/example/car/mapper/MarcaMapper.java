package com.example.car.mapper;

import com.example.car.dto.CreateMarcaDto;
import com.example.car.dto.RecoveryMarcaDto;
import com.example.car.dto.UpdateMarcaDto;
import com.example.car.entities.Marca;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = CarroMapper.class)
public interface MarcaMapper {
    Marca toMarca(CreateMarcaDto createMarcaDto);
    RecoveryMarcaDto toRecoveryMarcaDto(Marca marca);
    void updateMarcaFromDto(UpdateMarcaDto updateMarcaDto, @MappingTarget Marca marca);
}


