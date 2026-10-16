package com.uc.hotel.mapper;

import com.uc.hotel.dto.restaurant.RestaurantRequestDto;
import com.uc.hotel.dto.restaurant.RestaurantResponseDto;
import com.uc.hotel.entity.Restaurant;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper para transformar entre la entidad Restaurant y sus respectivos DTOs.
 */
@Component
public class RestaurantMapper {

    /**
     * Convierte un RestaurantRequestDto a una nueva entidad Restaurant.
     *
     * @param dto DTO con los datos de entrada.
     * @return Entidad Restaurant.
     */
    public Restaurant toEntity(RestaurantRequestDto dto) {
        if (dto == null) {
            return null;
        }
        return Restaurant.builder()
                .name(dto.getName())
                .address(dto.getAddress())
                .dishTypes(new ArrayList<>())
                .build();
    }

    /**
     * Convierte una entidad Restaurant a su correspondiente DTO de respuesta.
     *
     * @param entity Entidad Restaurant.
     * @return RestaurantResponseDto con la información a exponer.
     */
    public RestaurantResponseDto toResponseDto(Restaurant entity) {
        if (entity == null) {
            return null;
        }
        return RestaurantResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .address(entity.getAddress())
                .build();
    }

    /**
     * Convierte una lista de entidades Restaurant a una lista de DTOs de respuesta.
     *
     * @param entities Lista de entidades.
     * @return Lista de DTOs.
     */
    public List<RestaurantResponseDto> toResponseDtoList(List<Restaurant> entities) {
        if (entities == null) {
            return List.of();
        }
        return entities.stream().map(this::toResponseDto).toList();
    }

    /**
     * Actualiza los campos de una entidad Restaurant existente a partir de los datos del DTO.
     *
     * @param dto    DTO con los nuevos valores.
     * @param entity Entidad a actualizar.
     */
    public void updateEntityFromDto(RestaurantRequestDto dto, Restaurant entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
    }
}
