package com.uc.hotel.repository;

import com.uc.hotel.entity.DishType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio Spring Data JPA para la entidad DishType.
 */
@Repository
public interface DishTypeRepository extends JpaRepository<DishType, Long> {

    /**
     * Busca un tipo de plato por su nombre.
     *
     * @param name Nombre del plato.
     * @return Tipo de plato encontrado envuelto en Optional.
     */
    Optional<DishType> findByName(String name);

    /**
     * Obtiene todos los platos asociados a un restaurante dado.
     *
     * @param restaurantId Identificador del restaurante.
     * @return Lista de tipos de plato pertenecientes a dicho restaurante.
     */
    @Query(value = "SELECT * FROM dish_types WHERE id_restaurant = :restaurantId", nativeQuery = true)
    List<DishType> findByRestaurantId(@Param("restaurantId") Long restaurantId);
}
