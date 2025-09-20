package br.com.mmaverse.mapper;

import br.com.mmaverse.dto.ContenderDTO;
import br.com.mmaverse.dto.ContenderResponseDTO;
import br.com.mmaverse.entity.Contender;
import br.com.mmaverse.entity.Gym;
import lombok.experimental.UtilityClass;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class ContenderMapper {

    public static Contender toContender(ContenderDTO request) {
        List<Gym> gyms = request.gyms().stream().map(gymId -> Gym.builder()
                .id(gymId)
                .build()).toList();

        return Contender.builder()
                .name(request.name())
                .nickname(request.nickname())
                .cpf(request.cpf())
                .birthDate(request.birthDate())
                .weightClass(request.weightClass())
                .height(request.height())
                .reach(request.reach())
                .win(request.win())
                .lose(request.lose())
                .draw(request.draw())
                .gyms(gyms)
                .build();
    }

    public static ContenderResponseDTO toResponse(Contender contender) {
        String gymName = contender.getGyms().stream()
                .map(Gym::getName)
                .collect(Collectors.joining(", "));

        return ContenderResponseDTO.builder()
                .id(contender.getId())
                .name(contender.getName())
                .nickname(contender.getNickname())
                .cpf(contender.getCpf())
                .birthDate(contender.getBirthDate())
                .age(contender.getAge())
                .height(contender.getHeight())
                .reach(contender.getReach())
                .weightClass(contender.getWeightClass())
                .win(contender.getWin())
                .lose(contender.getLose())
                .draw(contender.getDraw())
                .gyms(gymName)
                .build();
    }
}
