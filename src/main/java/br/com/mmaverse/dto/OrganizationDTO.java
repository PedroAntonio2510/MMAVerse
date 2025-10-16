package br.com.mmaverse.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import org.hibernate.validator.constraints.br.CNPJ;

@Schema(name = "OrganizationDTO", description = "Data transfer object for an organization")
@Builder
public record OrganizationDTO(
        @Schema(description = "Name of the organization", example = "UFC")
        @NotBlank(message = "Name is necessary")
        String name,

        @Schema(description = "CNPJ of the organization", example = "00.000.000/0001-00")
        @CNPJ(message = "CNPJ is invalid")
        String cnpj
) {
}
