package com.uc.hotel.service;

import com.uc.hotel.dto.restaurant.RestaurantRequestDto;
import com.uc.hotel.dto.restaurant.RestaurantResponseDto;
import com.uc.hotel.entity.Restaurant;
import com.uc.hotel.exception.ApplicationException;
import com.uc.hotel.exception.ErrorCase;
import com.uc.hotel.mapper.RestaurantMapper;
import com.uc.hotel.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para la gestión de Restaurantes.
 */
@Service
@RequiredArgsConstructor
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final RestaurantMapper restaurantMapper;

    public RestaurantResponseDto create(RestaurantRequestDto dto) {
        Restaurant restaurant = restaurantMapper.toEntity(dto);
        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return restaurantMapper.toResponseDto(savedRestaurant);
    }

    public List<RestaurantResponseDto> findAll() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        return restaurantMapper.toResponseDtoList(restaurants);
    }

    private Restaurant findRestaurant(Long id) {
        return restaurantRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(
                        ErrorCase.NOT_FOUND,
                        "Restaurante no encontrado con id: " + id
                ));
    }

    public RestaurantResponseDto findById(Long id) {
        Restaurant restaurant = findRestaurant(id);
        return restaurantMapper.toResponseDto(restaurant);
    }

    public RestaurantResponseDto update(Long id, RestaurantRequestDto dto) {
        Restaurant restaurant = findRestaurant(id);
        restaurantMapper.updateEntityFromDto(dto, restaurant);
        Restaurant updatedRestaurant = restaurantRepository.save(restaurant);
        return restaurantMapper.toResponseDto(updatedRestaurant);
    }

    public void delete(Long id) {
        Restaurant restaurant = findRestaurant(id);
        restaurantRepository.delete(restaurant);
    }
}
