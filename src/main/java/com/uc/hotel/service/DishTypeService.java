package com.uc.hotel.service;

import com.uc.hotel.dto.dishtype.DishTypeRequestDto;
import com.uc.hotel.dto.dishtype.DishTypeResponseDto;
import com.uc.hotel.entity.DishType;
import com.uc.hotel.entity.Restaurant;
import com.uc.hotel.exception.ApplicationException;
import com.uc.hotel.exception.ErrorCase;
import com.uc.hotel.mapper.DishTypeMapper;
import com.uc.hotel.repository.DishTypeRepository;
import com.uc.hotel.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para la gestión de Tipos de Plato (DishType).
 */
@Service
@RequiredArgsConstructor
public class DishTypeService {

    private final DishTypeRepository dishTypeRepository;
    private final RestaurantRepository restaurantRepository;
    private final DishTypeMapper dishTypeMapper;

    public DishTypeResponseDto create(DishTypeRequestDto dto) {
        Restaurant restaurant = findRestaurant(dto.getRestaurantId());

        DishType dishType = dishTypeMapper.toEntity(dto);
        restaurant.getDishTypes().add(dishType);
        restaurantRepository.save(restaurant);

        return dishTypeMapper.toResponseDto(dishType, restaurant.getId());
    }

    public List<DishTypeResponseDto> findAll() {
        List<DishType> dishTypes = dishTypeRepository.findAll();
        return dishTypeMapper.toResponseDtoList(dishTypes);
    }

    private DishType findDishType(Long id) {
        return dishTypeRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(
                        ErrorCase.NOT_FOUND,
                        "Tipo de plato no encontrado con id: " + id
                ));
    }

    private Restaurant findRestaurant(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(
                        ErrorCase.NOT_FOUND,
                        "Restaurante no encontrado con id: " + id
                ));
    }

    public DishTypeResponseDto findById(Long id) {
        DishType dishType = findDishType(id);
        return dishTypeMapper.toResponseDto(dishType);
    }

    public List<DishTypeResponseDto> findByRestaurantId(Long restaurantId) {
        findRestaurant(restaurantId);
        List<DishType> dishes = dishTypeRepository.findByRestaurantId(restaurantId);
        return dishes.stream()
                .map(dish -> dishTypeMapper.toResponseDto(dish, restaurantId))
                .toList();
    }

    public DishTypeResponseDto update(Long id, DishTypeRequestDto dto) {
        DishType dishType = findDishType(id);
        dishTypeMapper.updateEntityFromDto(dto, dishType);
        DishType updatedDishType = dishTypeRepository.save(dishType);
        return dishTypeMapper.toResponseDto(updatedDishType, dto.getRestaurantId());
    }

    public void delete(Long id) {
        DishType dishType = findDishType(id);
        // Remueve la referencia en el restaurante si existe
        restaurantRepository.findAll().forEach(r -> r.getDishTypes().removeIf(d -> d.getId().equals(id)));
        dishTypeRepository.delete(dishType);
    }
}
