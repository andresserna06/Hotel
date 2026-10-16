package com.uc.hotel.mapper;

import com.uc.hotel.dto.dishtype.DishTypeRequestDto;
import com.uc.hotel.dto.dishtype.DishTypeResponseDto;
import com.uc.hotel.entity.DishType;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper para transformar entre la entidad DishType y sus respectivos DTOs.
 */
@Component
public class DishTypeMapper {

    /**
     * Convierte un DishTypeRequestDto a una nueva entidad DishType.
     *
     * @param dto DTO con los datos de entrada del plato.
     * @return Entidad DishType.
     */
    public DishType toEntity(DishTypeRequestDto dto) {
        if (dto == null) {
            return null;
        }
        return DishType.builder()
                .name(dto.getName())
                .description(dto.getDescription())
                .price(dto.getPrice())
                .purchasedDishes(new ArrayList<>())
                .build();
    }

    /**
     * Convierte una entidad DishType a su DTO de respuesta.
     *
     * @param entity Entidad DishType.
     * @return DishTypeResponseDto con los datos a exponer.
     */
    public DishTypeResponseDto toResponseDto(DishType entity) {
        if (entity == null) {
            return null;
        }
        return DishTypeResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .build();
    }

    /**
     * Convierte una entidad DishType a su DTO de respuesta vinculando el ID del restaurante.
     *
     * @param entity       Entidad DishType.
     * @param restaurantId Identificador del restaurante asociado.
     * @return DishTypeResponseDto completo.
     */
    public DishTypeResponseDto toResponseDto(DishType entity, Long restaurantId) {
        if (entity == null) {
            return null;
        }
        return DishTypeResponseDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .description(entity.getDescription())
                .price(entity.getPrice())
                .restaurantId(restaurantId)
                .build();
    }

    /**
     * Convierte una lista de entidades DishType a una lista de DTOs de respuesta.
     *
     * @param entities Lista de entidades.
     * @return Lista de DTOs.
     */
    public List<DishTypeResponseDto> toResponseDtoList(List<DishType> entities) {
        if (entities == null) {
            return List.of();
        }
        return entities.stream().map(this::toResponseDto).toList();
    }

    /**
     * Actualiza una entidad DishType existente con la información del DTO.
     *
     * @param dto    DTO con datos actualizados.
     * @param entity Entidad a actualizar.
     */
    public void updateEntityFromDto(DishTypeRequestDto dto, DishType entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPrice(dto.getPrice());
    }
}
