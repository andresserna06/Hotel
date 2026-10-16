package com.uc.hotel.mapper;

import com.uc.hotel.dto.reservation.ReservationRequestDto;
import com.uc.hotel.dto.reservation.ReservationResponseDto;
import com.uc.hotel.entity.Client;
import com.uc.hotel.entity.Reservation;
import com.uc.hotel.entity.Room;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Mapper para transformar entre la entidad Reservation y sus respectivos DTOs.
 */
@Component
@RequiredArgsConstructor
public class ReservationMapper {

    private final ClientMapper clientMapper;
    private final RoomMapper roomMapper;

    /**
     * Convierte un ReservationRequestDto y sus entidades relacionadas a una entidad Reservation.
     *
     * @param dto    DTO con datos de la reserva.
     * @param client Entidad Cliente previamente recuperada.
     * @param room   Entidad Habitación previamente recuperada.
     * @return Entidad Reservation construida.
     */
    public Reservation toEntity(ReservationRequestDto dto, Client client, Room room) {
        if (dto == null) {
            return null;
        }
        return Reservation.builder()
                .client(client)
                .room(room)
                .price(dto.getPrice())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .purchasedDishes(new ArrayList<>())
                .build();
    }

    /**
     * Convierte una entidad Reservation a su DTO de respuesta con los datos anidados de cliente y habitación.
     *
     * @param entity Entidad Reservation.
     * @return ReservationResponseDto listo para exponer.
     */
    public ReservationResponseDto toResponseDto(Reservation entity) {
        if (entity == null) {
            return null;
        }
        return ReservationResponseDto.builder()
                .id(entity.getId())
                .client(clientMapper.toResponseDto(entity.getClient()))
                .room(roomMapper.toResponseDto(entity.getRoom()))
                .price(entity.getPrice())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .build();
    }

    /**
     * Convierte una lista de entidades Reservation a una lista de DTOs de respuesta.
     *
     * @param entities Lista de entidades.
     * @return Lista de DTOs.
     */
    public List<ReservationResponseDto> toResponseDtoList(List<Reservation> entities) {
        if (entities == null) {
            return List.of();
        }
        return entities.stream().map(this::toResponseDto).toList();
    }

    /**
     * Actualiza los datos de una reserva existente.
     *
     * @param dto    DTO con nuevos valores.
     * @param entity Entidad Reservation a actualizar.
     * @param client Nueva entidad Client (si cambió).
     * @param room   Nueva entidad Room (si cambió).
     */
    public void updateEntityFromDto(ReservationRequestDto dto, Reservation entity, Client client, Room room) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setClient(client);
        entity.setRoom(room);
        entity.setPrice(dto.getPrice());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
    }
}
