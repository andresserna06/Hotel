package com.uc.hotel.dto.purchaseddish;

import com.uc.hotel.dto.dishtype.DishTypeResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO de salida (Response) que expone los datos de un Plato Comprado (PurchasedDish).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PurchasedDishResponseDto {

    private Long id;
    private DishTypeResponseDto dishType;
    private Long reservationId;
    private Integer quantity;
    private Boolean paid;
    private LocalDate date;
    private BigDecimal subtotal;
}
