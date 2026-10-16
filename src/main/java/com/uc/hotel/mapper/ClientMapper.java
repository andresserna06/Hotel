package com.uc.hotel.mapper;

import com.uc.hotel.dto.client.ClientRequestDto;
import com.uc.hotel.dto.client.ClientResponseDto;
import com.uc.hotel.entity.Client;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper para transformar entre la entidad Client y sus respectivos DTOs.
 */
@Component
public class ClientMapper {

    /**
     * Convierte un ClientRequestDto a una nueva entidad Client.
     *
     * @param dto DTO con los datos de entrada del cliente.
     * @return Entidad Client construida.
     */
    public Client toEntity(ClientRequestDto dto) {
        if (dto == null) {
            return null;
        }
        return Client.builder()
                .name(dto.getName())
                .document(dto.getDocument())
                .reservations(new ArrayList<>())
                .build();
    }

    /**
     * Convierte una entidad Client a su DTO de respuesta.
     *
     * @param entity Entidad Client.
     * @return ClientResponseDto con la información del cliente.
     */
    public ClientResponseDto toResponseDto(Client entity) {
        if (entity == null) {
            return null;
        }
        return ClientResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .document(entity.getDocument())
                .build();
    }

    /**
     * Convierte una lista de entidades Client a una lista de DTOs de respuesta.
     *
     * @param entities Lista de entidades.
     * @return Lista de DTOs.
     */
    public List<ClientResponseDto> toResponseDtoList(List<Client> entities) {
        if (entities == null) {
            return List.of();
        }
        return entities.stream().map(this::toResponseDto).toList();
    }

    /**
     * Actualiza los datos de un cliente existente con los valores del DTO.
     *
     * @param dto    DTO con datos actualizados.
     * @param entity Entidad a actualizar.
     */
    public void updateEntityFromDto(ClientRequestDto dto, Client entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setName(dto.getName());
        entity.setDocument(dto.getDocument());
    }
}
