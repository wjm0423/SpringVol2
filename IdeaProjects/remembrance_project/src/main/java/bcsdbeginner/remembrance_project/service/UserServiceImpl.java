package bcsdbeginner.remembrance_project.service;

import bcsdbeginner.remembrance_project.DTO.SignUpDTO;
import bcsdbeginner.remembrance_project.DTO.UserDTO;
import bcsdbeginner.remembrance_project.domain.JWT;
import bcsdbeginner.remembrance_project.repository.UserRepository;
import bcsdbeginner.remembrance_project.security.JWTProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JWTProvider jwtProvider;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public UserDTO signUp(SignUpDTO signUpDTO) {
        if (userRepository.existsByUsername(signUpDTO.getUsername())) {
            throw new IllegalArgumentException("이미 사용 중인 사용자 이름입니다.");
        }
        // password 암호화
        String encodedPassword = passwordEncoder.encode(signUpDTO.getPassword());
        List<String> roles = new ArrayList<>();
        roles.add("USER"); // USER 권한 부여
        return UserDTO.toDTO(userRepository.save(signUpDTO.toEntity(encodedPassword, roles)));
    }

    @Transactional
    @Override
    public JWT signIn(String username, String password) {
        // username + password를 기반으로 Authentication 객체 생성
        // 이때 authentication은 인증 여부를 확인하는 authenticated 값이 false
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

        // authenticate() 메서드를 통해 요청된 User에 대한 실제 검증 진행
        // authenticate() 메서드가 실행될 때 CustomUserDetailsService에서 만든 loadUserByUsername 메서드 실행
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 인증 정보를 기반으로 JWT 토큰 생성
        JWT jwt = jwtProvider.generateToken(authentication);

        return jwt;
    }
}
