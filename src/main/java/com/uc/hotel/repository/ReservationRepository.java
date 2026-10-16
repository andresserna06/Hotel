package com.uc.hotel.repository;

import com.uc.hotel.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Repositorio Spring Data JPA para la entidad Reservation.
 */
@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    /**
     * Obtiene todas las reservas asociadas a un cliente específico.
     *
     * @param clientId Identificador del cliente.
     * @return Lista de reservas del cliente.
     */
    List<Reservation> findByClientId(Long clientId);

    /**
     * Obtiene todas las reservas asociadas a una habitación específica.
     *
     * @param roomId Identificador de la habitación.
     * @return Lista de reservas de la habitación.
     */
    List<Reservation> findByRoomId(Long roomId);

    /**
     * Busca reservas que inicien dentro de un rango de fechas.
     *
     * @param start Fecha de inicio del rango.
     * @param end   Fecha fin del rango.
     * @return Lista de reservas encontradas.
     */
    List<Reservation> findByStartDateBetween(LocalDate start, LocalDate end);
}
