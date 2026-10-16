package com.uc.hotel.dto.dishtype;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * DTO de salida (Response) que expone los datos de un Tipo de Plato (DishType).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DishTypeResponseDto {

    private Long id;
    private String name;
    private String description;
    private BigDecimal price;
    private Long restaurantId;
}
