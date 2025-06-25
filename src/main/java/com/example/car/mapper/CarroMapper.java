
package com.example.car.mapper;

import com.example.car.dto.CreateCarroDto;
import com.example.car.dto.RecoveryCarroDto;
import com.example.car.dto.UpdateCarroDto;
import com.example.car.entities.Carro;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface CarroMapper {
    Carro toCarro(CreateCarroDto createCarroDto);

    @org.mapstruct.Mapping(source = "marca.id", target = "marcaId")
    RecoveryCarroDto toRecoveryCarroDto(Carro carro);
    void updateCarroFromDto(UpdateCarroDto updateCarroDto, @MappingTarget Carro carro);
}


