package com.uc.hotel.repository;

import com.uc.hotel.entity.PurchasedDish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repositorio Spring Data JPA para la entidad PurchasedDish.
 */
@Repository
public interface PurchasedDishRepository extends JpaRepository<PurchasedDish, Long> {

    /**
     * Obtiene los platos comprados vinculados a una reserva específica.
     *
     * @param reservationId Identificador de la reserva.
     * @return Lista de platos comprados en esa reserva.
     */
    List<PurchasedDish> findByReservationId(Long reservationId);

    /**
     * Obtiene las compras registradas de un tipo de plato específico.
     *
     * @param dishTypeId Identificador del tipo de plato.
     * @return Lista de compras realizadas de dicho plato.
     */
    List<PurchasedDish> findByDishTypeId(Long dishTypeId);

    /**
     * Filtra platos comprados según su estado de pago.
     *
     * @param paid true para platos pagados, false para pendientes de pago.
     * @return Lista de platos comprados con el estado indicado.
     */
    List<PurchasedDish> findByPaid(Boolean paid);
}
