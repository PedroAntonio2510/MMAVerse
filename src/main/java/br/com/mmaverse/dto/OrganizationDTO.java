package br.com.mmaverse.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

public record OrganizationDTO(
        @NotBlank(message = "Name is necessary")
        String name,
        @CNPJ(message = "CNPJ is invalid")
        String cnpj
) {
}
