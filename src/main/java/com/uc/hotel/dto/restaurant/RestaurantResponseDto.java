package com.uc.hotel.dto.restaurant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de salida (Response) que expone la información de un Restaurante.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RestaurantResponseDto {

    private Long id;
    private String name;
    private String address;
}
