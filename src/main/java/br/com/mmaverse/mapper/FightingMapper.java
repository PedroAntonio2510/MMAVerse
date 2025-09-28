package br.com.mmaverse.mapper;

import br.com.mmaverse.dto.FightingDTO;
import br.com.mmaverse.dto.FightingResponseDTO;
import br.com.mmaverse.entity.Contender;
import br.com.mmaverse.entity.Event;
import br.com.mmaverse.entity.Fighting;
import lombok.experimental.UtilityClass;

@UtilityClass
public class FightingMapper {

    public static Fighting toFighting(FightingDTO request) {

        Contender contenderBlueCorner = Contender.builder()
                .id(request.contenderBlueCorner()).build();

        Contender contenderRedCorner = Contender.builder()
                .id(request.contenderRedCorner()).build();

        Contender contenderWinner = Contender.builder()
                .id(request.contenderWinner()).build();

        Event organization = Event.builder().id(request.event()).build();

        return Fighting.builder()
                .event(organization)
                .contenderRedCorner(contenderRedCorner)
                .contenderBlueCorner(contenderBlueCorner)
                .winner(contenderWinner)
                .endRound(request.endRound())
                .endTime(request.endTime())
                .methodOfVictory(request.methodOfVictory())
                .build();
    }

    public static FightingResponseDTO toResponse(Fighting fighting) {
        return FightingResponseDTO.builder()
                .id(fighting.getId())
                .event(EventMapper.toDTO(fighting.getEvent()))
                .contenderRedCorner(ContenderMapper.toResponse(fighting.getContenderRedCorner()))
                .contenderBlueCorner(ContenderMapper.toResponse(fighting.getContenderBlueCorner()))
                .contenderWinner(fighting.getWinner() == null ? null : fighting.getWinner().getName())
                .methodOfVictory(fighting.getMethodOfVictory())
                .endRound(fighting.getEndRound())
                .endTime(fighting.getEndTime())
                .build();
    }
}
