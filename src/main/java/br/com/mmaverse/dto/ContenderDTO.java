package br.com.mmaverse.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.List;

public record ContenderDTO(
        @NotNull(message = "Name of the contender is necessary")
        String name,
        @NotNull(message = "Nickname of the contender is necessary, if don't have can set N/A")
        String nickname,
        @NotNull(message = "Contender birth date is necessary")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate birthDate,

        @NotNull(message = "CPF is necessary")
        @CPF(message = "CPF is invalid")
        String cpf,

        @NotNull(message = "Weight class is necessary")
        String weightClass,
        @NotNull(message = "Height is necessary")
        Double height,
        @NotNull(message = "Reach is necessary")
        Double reach,
        int win,
        int lose,
        int draw,
        List<Long> gyms)
{
}
