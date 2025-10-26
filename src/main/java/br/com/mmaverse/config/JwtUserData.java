package br.com.mmaverse.config;

import lombok.Builder;

@Builder
public record JwtUserData(Long id, String email) {
}
