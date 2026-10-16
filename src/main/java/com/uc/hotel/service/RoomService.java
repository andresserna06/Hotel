package com.uc.hotel.service;

import com.uc.hotel.dto.room.RoomRequestDto;
import com.uc.hotel.dto.room.RoomResponseDto;
import com.uc.hotel.entity.Room;
import com.uc.hotel.exception.ApplicationException;
import com.uc.hotel.exception.ErrorCase;
import com.uc.hotel.mapper.RoomMapper;
import com.uc.hotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para la gestión de Habitaciones (Room).
 */
@Service
@RequiredArgsConstructor
public class RoomService {

    private final RoomRepository roomRepository;
    private final RoomMapper roomMapper;

    public RoomResponseDto create(RoomRequestDto dto) {
        if (roomRepository.findByNumber(dto.getNumber()).isPresent()) {
            throw new ApplicationException(
                    ErrorCase.ALREADY_EXISTS,
                    "Ya existe una habitación registrada con el número: " + dto.getNumber()
            );
        }

        Room room = roomMapper.toEntity(dto);
        Room savedRoom = roomRepository.save(room);
        return roomMapper.toResponseDto(savedRoom);
    }

    public List<RoomResponseDto> findAll() {
        List<Room> rooms = roomRepository.findAll();
        return roomMapper.toResponseDtoList(rooms);
    }

    private Room findRoom(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(
                        ErrorCase.NOT_FOUND,
                        "Habitación no encontrada con id: " + id
                ));
    }

    public RoomResponseDto findById(Long id) {
        Room room = findRoom(id);
        return roomMapper.toResponseDto(room);
    }

    public RoomResponseDto findByNumber(String number) {
        Room room = roomRepository.findByNumber(number)
                .orElseThrow(() -> new ApplicationException(
                        ErrorCase.NOT_FOUND,
                        "Habitación no encontrada con número: " + number
                ));
        return roomMapper.toResponseDto(room);
    }

    public RoomResponseDto update(Long id, RoomRequestDto dto) {
        Room room = findRoom(id);

        roomRepository.findByNumber(dto.getNumber()).ifPresent(existing -> {
            if (!existing.getId().equals(id)) {
                throw new ApplicationException(
                        ErrorCase.ALREADY_EXISTS,
                        "El número de habitación '" + dto.getNumber() + "' ya está asignado a otra habitación"
                );
            }
        });

        roomMapper.updateEntityFromDto(dto, room);
        Room updatedRoom = roomRepository.save(room);
        return roomMapper.toResponseDto(updatedRoom);
    }

    public void delete(Long id) {
        Room room = findRoom(id);
        roomRepository.delete(room);
    }
}
