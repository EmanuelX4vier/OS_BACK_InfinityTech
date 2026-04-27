package com.infinity.os.service.user;

import com.infinity.os.dto.userdto.UserRequestDTO;
import com.infinity.os.dto.userdto.UserResponseDTO;
import com.infinity.os.dto.userdto.UserUpdateDTO;
import com.infinity.os.dto.userdto.auth.LoginRequestDTO;
import com.infinity.os.dto.userdto.auth.LoginResponseDTO;
import com.infinity.os.entity.User;
import com.infinity.os.exception.UserNotFoundException;
import com.infinity.os.exception.UserOrPassException;
import com.infinity.os.mapper.UserMapper;
import com.infinity.os.repository.UserRepository;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.security.auth.login.LoginException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDTO loginValidation(LoginRequestDTO dto){
        User user = userRepository.findByNome(dto.nome()).orElseThrow(UserOrPassException::new);
        if(!passwordEncoder.matches(dto.senha(), user.getSenha())){
            throw new UserOrPassException();
        }
        return new LoginResponseDTO(user.getId(), user.getNome(), user.getFuncao());
    }

    public UserResponseDTO createUser(UserRequestDTO dto) {

        //Cria a entidade.
        User user = userMapper.toEntity(dto);

        //Salva no banco.
        User savedUser = userRepository.save(user);

        //Retorna as informações em forma de DTO para o usuário.
        return userMapper.toResponseDTO(savedUser);
    }

    public UserResponseDTO searchUser (Long id){

        //Procura no banco.
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        //Retorna
        return userMapper.toResponseDTO(user);
    }

    public UserResponseDTO updateUser (Long id, UserUpdateDTO dto){

        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        // Só altera se o dado tiver sido enviado no JSON
        if (dto.getNome() != null) {
            user.setNome(dto.getNome());
        }
        if (dto.getFuncao() != null) {
            user.setFuncao(dto.getFuncao());
        }

        //Garante que o user foi atualizado.
        User updatedUser = userRepository.save(user);

        return userMapper.toResponseDTO(updatedUser);
    }

    public void deleteUser(Long id) {

        //Verifica se o user existe.
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException();
        }
        userRepository.deleteById(id);
    }

}