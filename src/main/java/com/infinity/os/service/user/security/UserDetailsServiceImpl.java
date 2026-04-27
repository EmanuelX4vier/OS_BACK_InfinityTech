package com.infinity.os.service.user.security;

import com.infinity.os.entity.User;
import com.infinity.os.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        User user = userRepository.findByNome(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado"));

        return new UserDetailsImpl(user);
    }
}