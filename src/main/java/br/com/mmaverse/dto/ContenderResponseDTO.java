package br.com.mmaverse.dto;

import br.com.mmaverse.entity.Gym;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

@Builder
@Schema(name = "ContenderResponseDTO", description = "Data transfer object for a contender response")
public record ContenderResponseDTO(
        @Schema(description = "ID of the contender", example = "1")
        Long id,

        @Schema(description = "Name of the contender", example = "Jon Jones")
        String name,

        @Schema(description = "Nickname of the contender", example = "Bones")
        String nickname,

        @Schema(description = "Birth date of the contender", example = "19/07/1987")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate birthDate,

        @Schema(description = "CPF of the contender", example = "123.456.789-00")
        String cpf,

        @Schema(description = "Age of the contender", example = "38")
        Integer age,

        @Schema(description = "Weight class of the contender", example = "Heavyweight")
        String weightClass,

        @Schema(description = "Height of the contender in meters", example = "1.93")
        Double height,

        @Schema(description = "Reach of the contender in meters", example = "2.15")
        Double reach,

        @Schema(description = "Number of wins", example = "27")
        int win,

        @Schema(description = "Number of losses", example = "1")
        int lose,

        @Schema(description = "Number of draws", example = "0")
        int draw,

        @Schema(description = "List of gyms the contender is a member of", example = "American Top Team, Jackson Wink MMA")
        String gyms
) {
}
