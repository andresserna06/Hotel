package com.uc.hotel.service;

import com.uc.hotel.dto.purchaseddish.PurchasedDishRequestDto;
import com.uc.hotel.dto.purchaseddish.PurchasedDishResponseDto;
import com.uc.hotel.entity.DishType;
import com.uc.hotel.entity.PurchasedDish;
import com.uc.hotel.entity.Reservation;
import com.uc.hotel.exception.ApplicationException;
import com.uc.hotel.exception.ErrorCase;
import com.uc.hotel.mapper.PurchasedDishMapper;
import com.uc.hotel.repository.DishTypeRepository;
import com.uc.hotel.repository.PurchasedDishRepository;
import com.uc.hotel.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para la gestión de Platos Comprados (PurchasedDish).
 */
@Service
@RequiredArgsConstructor
public class PurchasedDishService {

    private final PurchasedDishRepository purchasedDishRepository;
    private final DishTypeRepository dishTypeRepository;
    private final ReservationRepository reservationRepository;
    private final PurchasedDishMapper purchasedDishMapper;

    public PurchasedDishResponseDto create(PurchasedDishRequestDto dto) {
        DishType dishType = findDishType(dto.getDishTypeId());
        Reservation reservation = findReservation(dto.getReservationId());

        PurchasedDish purchasedDish = purchasedDishMapper.toEntity(dto, dishType, reservation);
        PurchasedDish savedPurchasedDish = purchasedDishRepository.save(purchasedDish);
        return purchasedDishMapper.toResponseDto(savedPurchasedDish);
    }

    public List<PurchasedDishResponseDto> findAll() {
        List<PurchasedDish> purchasedDishes = purchasedDishRepository.findAll();
        return purchasedDishMapper.toResponseDtoList(purchasedDishes);
    }

    private PurchasedDish findPurchasedDish(Long id) {
        return purchasedDishRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(
                        ErrorCase.NOT_FOUND,
                        "Plato comprado no encontrado con id: " + id
                ));
    }

    private DishType findDishType(Long id) {
        return dishTypeRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(
                        ErrorCase.NOT_FOUND,
                        "Tipo de plato no encontrado con id: " + id
                ));
    }

    private Reservation findReservation(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(
                        ErrorCase.NOT_FOUND,
                        "Reserva no encontrada con id: " + id
                ));
    }

    public PurchasedDishResponseDto findById(Long id) {
        PurchasedDish purchasedDish = findPurchasedDish(id);
        return purchasedDishMapper.toResponseDto(purchasedDish);
    }

    public List<PurchasedDishResponseDto> findByReservationId(Long reservationId) {
        findReservation(reservationId);
        List<PurchasedDish> dishes = purchasedDishRepository.findByReservationId(reservationId);
        return purchasedDishMapper.toResponseDtoList(dishes);
    }

    public List<PurchasedDishResponseDto> findByPaid(Boolean paid) {
        List<PurchasedDish> dishes = purchasedDishRepository.findByPaid(paid);
        return purchasedDishMapper.toResponseDtoList(dishes);
    }

    public PurchasedDishResponseDto update(Long id, PurchasedDishRequestDto dto) {
        PurchasedDish purchasedDish = findPurchasedDish(id);
        DishType dishType = findDishType(dto.getDishTypeId());
        Reservation reservation = findReservation(dto.getReservationId());

        purchasedDishMapper.updateEntityFromDto(dto, purchasedDish, dishType, reservation);
        PurchasedDish updatedPurchasedDish = purchasedDishRepository.save(purchasedDish);
        return purchasedDishMapper.toResponseDto(updatedPurchasedDish);
    }

    public void delete(Long id) {
        PurchasedDish purchasedDish = findPurchasedDish(id);
        purchasedDishRepository.delete(purchasedDish);
    }
}
