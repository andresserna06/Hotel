package com.uc.hotel.dto.client;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO de entrada (Request) para registrar o actualizar un Cliente (Client).
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientRequestDto {

    @NotBlank(message = "El nombre del cliente no puede estar vacío")
    @Size(max = 100, message = "El nombre no puede exceder los 100 caracteres")
    private String name;

    @NotBlank(message = "El documento no puede estar vacío")
    @Size(max = 50, message = "El documento no puede exceder los 50 caracteres")
    private String document;
}
