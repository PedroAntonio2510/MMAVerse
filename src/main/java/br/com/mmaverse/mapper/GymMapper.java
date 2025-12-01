package br.com.mmaverse.mapper;

import br.com.mmaverse.controller.dto.GymDTO;
import br.com.mmaverse.controller.dto.GymResponseDTO;
import br.com.mmaverse.entity.Gym;
import lombok.experimental.UtilityClass;

@UtilityClass
public class GymMapper {

    public static Gym toGym(GymDTO request) {
        return Gym.builder()
                .name(request.name())
                .location(request.location())
                .foundation(request.foundation())
                .build();
    }

    public static GymResponseDTO toGymResponse(Gym gym) {
        return GymResponseDTO.builder()
                .id(gym.getId())
                .name(gym.getName())
                .foundation(gym.getFoundation())
                .location(gym.getLocation())
                .contenders(gym.getContenders().stream().map(ContenderMapper::toResponse).toList())
                .build();
    }
}
