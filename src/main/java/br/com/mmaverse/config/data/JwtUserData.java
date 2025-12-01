package br.com.mmaverse.config.data;

import lombok.Builder;

@Builder
public record JwtUserData(Long id, String email) {
}
