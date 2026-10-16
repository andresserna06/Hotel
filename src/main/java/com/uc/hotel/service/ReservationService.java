package com.uc.hotel.service;

import com.uc.hotel.dto.reservation.ReservationRequestDto;
import com.uc.hotel.dto.reservation.ReservationResponseDto;
import com.uc.hotel.entity.Client;
import com.uc.hotel.entity.Reservation;
import com.uc.hotel.entity.Room;
import com.uc.hotel.exception.ApplicationException;
import com.uc.hotel.exception.ErrorCase;
import com.uc.hotel.mapper.ReservationMapper;
import com.uc.hotel.repository.ClientRepository;
import com.uc.hotel.repository.ReservationRepository;
import com.uc.hotel.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para la gestión de Reservas (Reservation).
 */
@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final ClientRepository clientRepository;
    private final RoomRepository roomRepository;
    private final ReservationMapper reservationMapper;

    public ReservationResponseDto create(ReservationRequestDto dto) {
        validateDates(dto);

        Client client = findClient(dto.getClientId());
        Room room = findRoom(dto.getRoomId());

        Reservation reservation = reservationMapper.toEntity(dto, client, room);
        Reservation savedReservation = reservationRepository.save(reservation);
        return reservationMapper.toResponseDto(savedReservation);
    }

    public List<ReservationResponseDto> findAll() {
        List<Reservation> reservations = reservationRepository.findAll();
        return reservationMapper.toResponseDtoList(reservations);
    }

    private Reservation findReservation(Long id) {
        return reservationRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(
                        ErrorCase.NOT_FOUND,
                        "Reserva no encontrada con id: " + id
                ));
    }

    private Client findClient(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(
                        ErrorCase.NOT_FOUND,
                        "Cliente no encontrado con id: " + id
                ));
    }

    private Room findRoom(Long id) {
        return roomRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(
                        ErrorCase.NOT_FOUND,
                        "Habitación no encontrada con id: " + id
                ));
    }

    public ReservationResponseDto findById(Long id) {
        Reservation reservation = findReservation(id);
        return reservationMapper.toResponseDto(reservation);
    }

    public List<ReservationResponseDto> findByClientId(Long clientId) {
        findClient(clientId);
        List<Reservation> reservations = reservationRepository.findByClientId(clientId);
        return reservationMapper.toResponseDtoList(reservations);
    }

    public List<ReservationResponseDto> findByRoomId(Long roomId) {
        findRoom(roomId);
        List<Reservation> reservations = reservationRepository.findByRoomId(roomId);
        return reservationMapper.toResponseDtoList(reservations);
    }

    public ReservationResponseDto update(Long id, ReservationRequestDto dto) {
        validateDates(dto);

        Reservation reservation = findReservation(id);
        Client client = findClient(dto.getClientId());
        Room room = findRoom(dto.getRoomId());

        reservationMapper.updateEntityFromDto(dto, reservation, client, room);
        Reservation updatedReservation = reservationRepository.save(reservation);
        return reservationMapper.toResponseDto(updatedReservation);
    }

    public void delete(Long id) {
        Reservation reservation = findReservation(id);
        reservationRepository.delete(reservation);
    }

    private void validateDates(ReservationRequestDto dto) {
        if (dto.getStartDate().isAfter(dto.getEndDate())) {
            throw new ApplicationException(
                    ErrorCase.INVALID_OPERATION,
                    "La fecha de inicio (" + dto.getStartDate() + ") no puede ser posterior a la fecha final (" + dto.getEndDate() + ")"
            );
        }
    }
}
