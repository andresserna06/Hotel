package com.uc.hotel.repository;

import com.uc.hotel.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio Spring Data JPA para la entidad Restaurant.
 */
@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    /**
     * Busca un restaurante por su nombre.
     *
     * @param name Nombre del restaurante.
     * @return Restaurante encontrado envuelto en Optional.
     */
    Optional<Restaurant> findByName(String name);
}
