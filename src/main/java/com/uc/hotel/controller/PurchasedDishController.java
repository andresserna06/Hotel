package com.uc.hotel.controller;

import com.uc.hotel.dto.purchaseddish.PurchasedDishRequestDto;
import com.uc.hotel.dto.purchaseddish.PurchasedDishResponseDto;
import com.uc.hotel.service.PurchasedDishService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST para operaciones CRUD sobre Platos Comprados (PurchasedDish).
 */
@RestController
@RequestMapping("/api/purchased-dishes")
@RequiredArgsConstructor
public class PurchasedDishController {

    private final PurchasedDishService purchasedDishService;

    @PostMapping
    public ResponseEntity<PurchasedDishResponseDto> create(@Valid @RequestBody PurchasedDishRequestDto dto) {
        PurchasedDishResponseDto response = purchasedDishService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<PurchasedDishResponseDto>> findAll() {
        return ResponseEntity.ok(purchasedDishService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PurchasedDishResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(purchasedDishService.findById(id));
    }

    @GetMapping("/reservation/{reservationId}")
    public ResponseEntity<List<PurchasedDishResponseDto>> findByReservationId(@PathVariable Long reservationId) {
        return ResponseEntity.ok(purchasedDishService.findByReservationId(reservationId));
    }

    @GetMapping("/paid")
    public ResponseEntity<List<PurchasedDishResponseDto>> findByPaid(@RequestParam Boolean paid) {
        return ResponseEntity.ok(purchasedDishService.findByPaid(paid));
    }

    @PutMapping("/{id}")
    public ResponseEntity<PurchasedDishResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody PurchasedDishRequestDto dto) {
        return ResponseEntity.ok(purchasedDishService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        purchasedDishService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
