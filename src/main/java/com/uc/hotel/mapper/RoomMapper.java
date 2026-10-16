package com.uc.hotel.mapper;

import com.uc.hotel.dto.room.RoomRequestDto;
import com.uc.hotel.dto.room.RoomResponseDto;
import com.uc.hotel.entity.Room;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper para transformar entre la entidad Room y sus respectivos DTOs.
 */
@Component
public class RoomMapper {

    /**
     * Convierte un RoomRequestDto a una nueva entidad Room.
     *
     * @param dto DTO con los datos de entrada de la habitación.
     * @return Entidad Room construida.
     */
    public Room toEntity(RoomRequestDto dto) {
        if (dto == null) {
            return null;
        }
        return Room.builder()
                .number(dto.getNumber())
                .capacity(dto.getCapacity())
                .reservations(new ArrayList<>())
                .build();
    }

    /**
     * Convierte una entidad Room a su DTO de respuesta.
     *
     * @param entity Entidad Room.
     * @return RoomResponseDto con la información a exponer.
     */
    public RoomResponseDto toResponseDto(Room entity) {
        if (entity == null) {
            return null;
        }
        return RoomResponseDto.builder()
                .id(entity.getId())
                .number(entity.getNumber())
                .capacity(entity.getCapacity())
                .build();
    }

    /**
     * Convierte una lista de entidades Room a una lista de DTOs de respuesta.
     *
     * @param entities Lista de entidades.
     * @return Lista de DTOs.
     */
    public List<RoomResponseDto> toResponseDtoList(List<Room> entities) {
        if (entities == null) {
            return List.of();
        }
        return entities.stream().map(this::toResponseDto).toList();
    }

    /**
     * Actualiza una entidad Room existente con los datos del DTO.
     *
     * @param dto    DTO con datos actualizados.
     * @param entity Entidad a actualizar.
     */
    public void updateEntityFromDto(RoomRequestDto dto, Room entity) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setNumber(dto.getNumber());
        entity.setCapacity(dto.getCapacity());
    }
}
