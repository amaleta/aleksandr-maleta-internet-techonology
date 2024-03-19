package com.singidunum.delivery.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Запрос на refresh")
public class RefreshRequest {
    @Schema(description = "Токен доступа", example = "11c4ef9b-5a3b-4b4b-a038-7aab1ef79b76")
    private String refreshToken;
}