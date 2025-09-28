package br.com.mmaverse.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;
import java.util.List;

@Schema(name = "ContenderDTO", description = "Data transfer object for a contender")
public record ContenderDTO(
        @Schema(description = "Name of the contender", example = "Jon Jones")
        @NotNull(message = "Name of the contender is necessary")
        String name,

        @Schema(description = "Nickname of the contender", example = "Bones")
        @NotNull(message = "Nickname of the contender is necessary, if don't have can set N/A")
        String nickname,

        @Schema(description = "Birth date of the contender", example = "19/07/1987")
        @NotNull(message = "Contender birth date is necessary")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate birthDate,

        @Schema(description = "CPF of the contender", example = "123.456.789-00")
        @NotNull(message = "CPF is necessary")
        @CPF(message = "CPF is invalid")
        String cpf,

        @Schema(description = "Weight class of the contender", example = "Heavyweight")
        @NotNull(message = "Weight class is necessary")
        String weightClass,

        @Schema(description = "Height of the contender in meters", example = "1.93")
        @NotNull(message = "Height is necessary")
        Double height,

        @Schema(description = "Reach of the contender in meters", example = "2.15")
        @NotNull(message = "Reach is necessary")
        Double reach,

        @Schema(description = "Number of wins", example = "27")
        int win,

        @Schema(description = "Number of losses", example = "1")
        int lose,

        @Schema(description = "Number of draws", example = "0")
        int draw,

        @Schema(description = "List of gym IDs", example = "[1, 2]")
        List<Long> gyms)
{
}
