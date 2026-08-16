package com.api.formulario_cadastrov2.dto;

import jakarta.validation.constraints.NotBlank;

public record LoginDto(
    @NotBlank String email,
    @NotBlank String senha
) {}