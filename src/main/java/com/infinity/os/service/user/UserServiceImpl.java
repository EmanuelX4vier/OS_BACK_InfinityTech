package com.infinity.os.service.user;

import com.infinity.os.dto.userdto.UserResponseDTO;
import com.infinity.os.dto.userdto.UserUpdateDTO;
import com.infinity.os.entity.User;
import com.infinity.os.exception.UserNotFoundException;
import com.infinity.os.mapper.UserMapper;
import com.infinity.os.repository.UserRepository;
import lombok.*;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    //CRUD
    //Create *Somente em AuthService*

    //Read
    public UserResponseDTO searchUser (Long id){

        //Procura no banco.
        User user = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

        //Retorna
        return userMapper.toResponseDTO(user);
    }

    //Update
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

    //Delete
    public void deleteUser(Long id) {

        //Verifica se o user existe.
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException();
        }
        userRepository.deleteById(id);
    }

}