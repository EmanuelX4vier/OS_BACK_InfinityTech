package com.infinity.os.mapper;

import com.infinity.os.dto.userdto.UserRequestDTO;
import com.infinity.os.dto.userdto.UserResponseDTO;
import com.infinity.os.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMapper {

    private final PasswordEncoder passwordEncoder;

    public User toEntity (UserRequestDTO dto){
        String senha = passwordEncoder.encode(dto.getSenha());
        User user = User.builder().nome(dto.getNome()).senha(senha).funcao(dto.getFuncao()).build();
        return user;
    }

    public UserResponseDTO toResponseDTO(User entity){
        UserResponseDTO userResponseDTO = new UserResponseDTO(entity.getId(), entity.getNome(), entity.getFuncao(), entity.getDataCadastro());
        return userResponseDTO;
    }
}
