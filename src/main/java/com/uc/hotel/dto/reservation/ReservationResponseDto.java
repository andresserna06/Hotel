package com.uc.hotel.dto.reservation;

import com.uc.hotel.dto.client.ClientResponseDto;
import com.uc.hotel.dto.room.RoomResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * DTO de salida (Response) que expone la información detallada de una Reserva (Reservation).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReservationResponseDto {

    private Long id;
    private ClientResponseDto client;
    private RoomResponseDto room;
    private BigDecimal price;
    private LocalDate startDate;
    private LocalDate endDate;
}
