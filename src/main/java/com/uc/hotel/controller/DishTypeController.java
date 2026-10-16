package com.uc.hotel.controller;

import com.uc.hotel.dto.dishtype.DishTypeRequestDto;
import com.uc.hotel.dto.dishtype.DishTypeResponseDto;
import com.uc.hotel.service.DishTypeService;
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
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controlador REST para operaciones CRUD sobre Tipos de Plato (DishType).
 */
@RestController
@RequestMapping("/api/dish-types")
@RequiredArgsConstructor
public class DishTypeController {

    private final DishTypeService dishTypeService;

    @PostMapping
    public ResponseEntity<DishTypeResponseDto> create(@Valid @RequestBody DishTypeRequestDto dto) {
        DishTypeResponseDto response = dishTypeService.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<DishTypeResponseDto>> findAll() {
        return ResponseEntity.ok(dishTypeService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DishTypeResponseDto> findById(@PathVariable Long id) {
        return ResponseEntity.ok(dishTypeService.findById(id));
    }

    @GetMapping("/restaurant/{restaurantId}")
    public ResponseEntity<List<DishTypeResponseDto>> findByRestaurantId(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(dishTypeService.findByRestaurantId(restaurantId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DishTypeResponseDto> update(
            @PathVariable Long id,
            @Valid @RequestBody DishTypeRequestDto dto) {
        return ResponseEntity.ok(dishTypeService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        dishTypeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
