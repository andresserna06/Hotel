package com.uc.hotel.service;

import com.uc.hotel.dto.client.ClientRequestDto;
import com.uc.hotel.dto.client.ClientResponseDto;
import com.uc.hotel.entity.Client;
import com.uc.hotel.exception.ApplicationException;
import com.uc.hotel.exception.ErrorCase;
import com.uc.hotel.mapper.ClientMapper;
import com.uc.hotel.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Servicio para la gestión de Clientes (Client).
 */
@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientResponseDto create(ClientRequestDto dto) {
        if (clientRepository.existsByDocument(dto.getDocument())) {
            throw new ApplicationException(
                    ErrorCase.ALREADY_EXISTS,
                    "Ya existe un cliente registrado con el documento: " + dto.getDocument()
            );
        }

        Client client = clientMapper.toEntity(dto);
        Client savedClient = clientRepository.save(client);
        return clientMapper.toResponseDto(savedClient);
    }

    public List<ClientResponseDto> findAll() {
        List<Client> clients = clientRepository.findAll();
        return clientMapper.toResponseDtoList(clients);
    }

    private Client findClient(Long id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(
                        ErrorCase.NOT_FOUND,
                        "Cliente no encontrado con id: " + id
                ));
    }

    public ClientResponseDto findById(Long id) {
        Client client = findClient(id);
        return clientMapper.toResponseDto(client);
    }

    public ClientResponseDto findByDocument(String document) {
        Client client = clientRepository.findByDocument(document)
                .orElseThrow(() -> new ApplicationException(
                        ErrorCase.NOT_FOUND,
                        "Cliente no encontrado con documento: " + document
                ));
        return clientMapper.toResponseDto(client);
    }

    public ClientResponseDto update(Long id, ClientRequestDto dto) {
        Client client = findClient(id);

        if (!client.getDocument().equalsIgnoreCase(dto.getDocument()) && clientRepository.existsByDocument(dto.getDocument())) {
            throw new ApplicationException(
                    ErrorCase.ALREADY_EXISTS,
                    "El documento '" + dto.getDocument() + "' ya pertenece a otro cliente"
            );
        }

        clientMapper.updateEntityFromDto(dto, client);
        Client updatedClient = clientRepository.save(client);
        return clientMapper.toResponseDto(updatedClient);
    }

    public void delete(Long id) {
        Client client = findClient(id);
        clientRepository.delete(client);
    }
}
