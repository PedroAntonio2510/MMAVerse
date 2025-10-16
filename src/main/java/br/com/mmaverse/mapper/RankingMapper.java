package br.com.mmaverse.mapper;

import br.com.mmaverse.dto.RankingDTO;
import br.com.mmaverse.dto.RankingResponseDTO;
import br.com.mmaverse.entity.Contender;
import br.com.mmaverse.entity.Organization;
import br.com.mmaverse.entity.Ranking;
import lombok.experimental.UtilityClass;

import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
public class RankingMapper {

    public static Ranking toRanking(RankingDTO rankingDTO) {
        Set<Contender> contenders = rankingDTO.contenders().stream().map(contenderId -> Contender.builder().id(contenderId).build()).collect(Collectors.toSet());
        return Ranking.builder()
                .name(rankingDTO.name())
                .category(rankingDTO.category())
                .organization(Organization.builder().id(rankingDTO.organizationId()).build())
                .contenders(contenders)
                .build();
    }

    public static RankingResponseDTO toResponse(Ranking ranking) {
        return RankingResponseDTO.builder()
                .id(ranking.getId())
                .name(ranking.getName())
                .category(ranking.getCategory())
                .organization(OrganizationMapper.toDTO(ranking.getOrganization()))
                .contenders(ranking.getContenders().stream()
                        .map(ContenderMapper::toResponse)
                        .collect(Collectors.toSet()))
                .updatedAt(ranking.getUpdatedAt())
                .build();
    }
}
