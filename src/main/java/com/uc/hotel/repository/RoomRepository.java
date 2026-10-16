package com.uc.hotel.repository;

import com.uc.hotel.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio Spring Data JPA para la entidad Room.
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    /**
     * Busca una habitación por su número identificador.
     *
     * @param number Número de habitación (ej. "101").
     * @return Habitación encontrada envuelta en Optional.
     */
    Optional<Room> findByNumber(String number);

    /**
     * Obtiene habitaciones que tengan una capacidad mínima determinada.
     *
     * @param capacity Capacidad mínima de personas.
     * @return Lista de habitaciones que cumplen con el criterio.
     */
    List<Room> findByCapacityGreaterThanEqual(Integer capacity);
}
