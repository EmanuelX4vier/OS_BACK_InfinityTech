package com.infinity.os.service.user;

import com.infinity.os.dto.userdto.UserResponseDTO;
import com.infinity.os.dto.userdto.UserUpdateDTO;

public interface UserService {
    UserResponseDTO searchUser (Long id);
    UserResponseDTO updateUser (Long id, UserUpdateDTO dto);
    void deleteUser(Long id);
}
