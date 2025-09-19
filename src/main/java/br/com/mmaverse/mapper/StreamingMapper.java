package br.com.mmaverse.mapper;

import br.com.mmaverse.dto.StreamingDTO;
import br.com.mmaverse.entity.Streaming;
import lombok.experimental.UtilityClass;

import java.util.stream.Stream;

@UtilityClass
public class StreamingMapper {

    public static Streaming toStreaming(StreamingDTO request) {
        return Streaming.builder()
                .name(request.name())
                .build();
    }

}
