package com.uc.hotel.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dish_types")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DishType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_dish_type")
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    /**
     * Relación bidireccional con PlatoComprado (PurchasedDish),
     * resolviendo la relación N:N con Reserva (Reservation).
     */
    @OneToMany(mappedBy = "dishType", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PurchasedDish> purchasedDishes = new ArrayList<>();
}
