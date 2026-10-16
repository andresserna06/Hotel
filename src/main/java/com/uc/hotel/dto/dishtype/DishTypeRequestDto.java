package com.uc.hotel.dto.dishtype;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO de entrada (Request) para registrar o actualizar un Tipo de Plato (DishType).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DishTypeRequestDto {

    @NotNull(message = "El ID del restaurante asociado es obligatorio")
    private Long restaurantId;

    @NotBlank(message = "El nombre del plato no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    private String name;

    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres")
    private String description;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    private BigDecimal price;
}
