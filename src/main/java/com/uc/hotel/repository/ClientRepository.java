package com.uc.hotel.repository;

import com.uc.hotel.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repositorio Spring Data JPA para la entidad Client.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    /**
     * Busca un cliente a partir de su documento de identidad.
     *
     * @param document Número o código de documento.
     * @return Cliente encontrado envuelto en Optional.
     */
    Optional<Client> findByDocument(String document);

    /**
     * Verifica si ya existe un cliente registrado con el documento especificado.
     *
     * @param document Número o código de documento.
     * @return true si ya existe, false en caso contrario.
     */
    boolean existsByDocument(String document);
}
