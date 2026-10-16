package com.uc.hotel.dto.room;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de entrada (Request) para registrar o actualizar una Habitación (Room).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RoomRequestDto {

    @NotBlank(message = "El número de habitación es obligatorio")
    @Size(max = 20, message = "El número de habitación no puede exceder los 20 caracteres")
    private String number;

    @NotNull(message = "La capacidad es obligatoria")
    @Min(value = 1, message = "La capacidad debe ser de al menos 1 persona")
    private Integer capacity;
}
