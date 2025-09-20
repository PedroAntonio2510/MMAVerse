package br.com.mmaverse.dto;

import br.com.mmaverse.entity.Gym;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
public record ContenderResponseDTO(
        Long id,
        String name,
        String nickname,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate birthDate,
        String cpf,
        Integer age,
        String weightClass,
        Double height,
        Double reach,
        int win,
        int lose,
        int draw,
        String gyms
) {
}
