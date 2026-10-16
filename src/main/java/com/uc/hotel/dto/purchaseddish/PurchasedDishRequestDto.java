package com.uc.hotel.dto.purchaseddish;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * DTO de entrada (Request) para registrar o actualizar un Plato Comprado (PurchasedDish).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchasedDishRequestDto {

    @NotNull(message = "El ID del tipo de plato es obligatorio")
    private Long dishTypeId;

    @NotNull(message = "El ID de la reserva es obligatorio")
    private Long reservationId;

    @NotNull(message = "La cantidad es obligatoria")
    @Min(value = 1, message = "La cantidad debe ser de al menos 1 unidad")
    private Integer quantity;

    @NotNull(message = "El indicador de pagado es obligatorio")
    private Boolean paid;

    @NotNull(message = "La fecha de compra es obligatoria")
    private LocalDate date;
}
