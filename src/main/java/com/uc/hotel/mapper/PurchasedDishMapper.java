package com.uc.hotel.mapper;

import com.uc.hotel.dto.purchaseddish.PurchasedDishRequestDto;
import com.uc.hotel.dto.purchaseddish.PurchasedDishResponseDto;
import com.uc.hotel.entity.DishType;
import com.uc.hotel.entity.PurchasedDish;
import com.uc.hotel.entity.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Mapper para transformar entre la entidad PurchasedDish y sus respectivos DTOs.
 */
@Component
@RequiredArgsConstructor
public class PurchasedDishMapper {

    private final DishTypeMapper dishTypeMapper;

    /**
     * Convierte un PurchasedDishRequestDto y sus entidades asociadas a una entidad PurchasedDish.
     *
     * @param dto         DTO con los datos de compra del plato.
     * @param dishType    Entidad DishType asociada.
     * @param reservation Entidad Reservation asociada.
     * @return Entidad PurchasedDish construida.
     */
    public PurchasedDish toEntity(PurchasedDishRequestDto dto, DishType dishType, Reservation reservation) {
        if (dto == null) {
            return null;
        }
        return PurchasedDish.builder()
                .dishType(dishType)
                .reservation(reservation)
                .quantity(dto.getQuantity())
                .paid(dto.getPaid())
                .date(dto.getDate())
                .build();
    }

    /**
     * Convierte una entidad PurchasedDish a su correspondiente DTO de respuesta,
     * calculando automáticamente el subtotal (precio x cantidad).
     *
     * @param entity Entidad PurchasedDish.
     * @return PurchasedDishResponseDto con información detallada y subtotal.
     */
    public PurchasedDishResponseDto toResponseDto(PurchasedDish entity) {
        if (entity == null) {
            return null;
        }

        BigDecimal subtotal = BigDecimal.ZERO;
        if (entity.getDishType() != null && entity.getDishType().getPrice() != null && entity.getQuantity() != null) {
            subtotal = entity.getDishType().getPrice().multiply(BigDecimal.valueOf(entity.getQuantity()));
        }

        return PurchasedDishResponseDto.builder()
                .id(entity.getId())
                .dishType(dishTypeMapper.toResponseDto(entity.getDishType()))
                .reservationId(entity.getReservation() != null ? entity.getReservation().getId() : null)
                .quantity(entity.getQuantity())
                .paid(entity.getPaid())
                .date(entity.getDate())
                .subtotal(subtotal)
                .build();
    }

    /**
     * Convierte una lista de entidades PurchasedDish a una lista de DTOs de respuesta.
     *
     * @param entities Lista de entidades.
     * @return Lista de DTOs.
     */
    public List<PurchasedDishResponseDto> toResponseDtoList(List<PurchasedDish> entities) {
        if (entities == null) {
            return List.of();
        }
        return entities.stream().map(this::toResponseDto).toList();
    }

    /**
     * Actualiza los datos de un plato comprado existente.
     *
     * @param dto         DTO con datos actualizados.
     * @param entity      Entidad PurchasedDish a actualizar.
     * @param dishType    Entidad DishType asociada (si cambió).
     * @param reservation Entidad Reservation asociada (si cambió).
     */
    public void updateEntityFromDto(PurchasedDishRequestDto dto, PurchasedDish entity, DishType dishType, Reservation reservation) {
        if (dto == null || entity == null) {
            return;
        }
        entity.setDishType(dishType);
        entity.setReservation(reservation);
        entity.setQuantity(dto.getQuantity());
        entity.setPaid(dto.getPaid());
        entity.setDate(dto.getDate());
    }
}
