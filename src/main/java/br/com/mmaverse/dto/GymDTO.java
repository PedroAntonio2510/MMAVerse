package br.com.mmaverse.dto;

import lombok.Builder;

@Builder
public record GymDTO(
        String name,
        String location,
        Integer foundation
) {
}
