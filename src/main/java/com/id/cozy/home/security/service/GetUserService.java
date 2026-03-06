package com.id.cozy.home.security.service;

import com.id.cozy.home.security.entity.UserEntity;
import com.id.cozy.home.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.id.cozy.home.security.util.JwtUtil.getUsername;

/**
 * @author martin
 * Date 06/03/26
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class GetUserService {
    private final UserRepository userRepository;

    public UserEntity getUser() {
        return userRepository.findByUsername(getUsername()).orElseThrow(()-> new SecurityException("User not found"));
    }
}
