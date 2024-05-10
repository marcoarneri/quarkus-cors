package org.acme.adapter.rest.mapper;

import org.acme.adapter.repository.entity.ClienteEntity;
import org.acme.adapter.rest.model.ClienteRequest;
import org.acme.adapter.rest.model.ClienteResponse;
import org.acme.domain.service.model.ClienteRequestDto;
import org.acme.domain.service.model.ClienteResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA)
public interface MapperCliente {

    MapperCliente INSTANCE = Mappers.getMapper(MapperCliente.class);

    ClienteRequestDto toRequestDto(ClienteRequest request);

    ClienteEntity toEntity(ClienteRequestDto requestDto);

    List<ClienteResponseDto> toResponseDtoList(List<ClienteEntity> entityList);

    ClienteResponseDto toResponseDto(ClienteEntity entity);

    List<ClienteResponse> toResponseList(List<ClienteResponseDto> responseDtoList);

    ClienteResponse toResponse(ClienteResponseDto responseDto);

}
