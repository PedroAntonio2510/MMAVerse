package br.com.mmaverse.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.List;

public record ContenderDTO(
        String name,
        String nickname,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate birthDate,
        String cpf,
        String weightClass,
        Double height,
        Double reach,
        int win,
        int lose,
        int draw,
        List<Long> gyms)
{
}
