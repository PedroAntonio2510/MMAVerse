package br.com.mmaverse.mapper;

import br.com.mmaverse.dto.UserRequestDTO;
import br.com.mmaverse.entity.User;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public static User toUser(UserRequestDTO request) {
        return User.builder()
                .email(request.email())
                .password(request.password())
                .build();
    }
}
