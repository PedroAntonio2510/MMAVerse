package br.com.mmaverse.mapper;

import br.com.mmaverse.dto.GymDTO;
import br.com.mmaverse.dto.StreamingDTO;
import br.com.mmaverse.entity.Gym;
import br.com.mmaverse.entity.Streaming;
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
}
