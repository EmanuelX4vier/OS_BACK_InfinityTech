package com.infinity.os.controller;

import com.infinity.os.dto.userdto.UserResponseDTO;
import com.infinity.os.dto.userdto.UserUpdateDTO;
import com.infinity.os.service.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> searchUser(@PathVariable Long userId) {
        UserResponseDTO user = userService.searchUser(userId);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserResponseDTO> updateUser(@PathVariable Long userId,
                                      @RequestBody @Valid UserUpdateDTO dto) {
        UserResponseDTO update = userService.updateUser(userId, dto);
        return ResponseEntity.ok(update);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
    }
}