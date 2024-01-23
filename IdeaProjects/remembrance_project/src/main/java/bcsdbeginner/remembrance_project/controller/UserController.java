package bcsdbeginner.remembrance_project.controller;

import bcsdbeginner.remembrance_project.DTO.SignInDTO;
import bcsdbeginner.remembrance_project.DTO.SignUpDTO;
import bcsdbeginner.remembrance_project.DTO.UserDTO;
import bcsdbeginner.remembrance_project.domain.JWT;
import bcsdbeginner.remembrance_project.security.SecurityUtil;
import bcsdbeginner.remembrance_project.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<UserDTO> signUp(@RequestBody SignUpDTO signUpDTO) {
        UserDTO savedUserDTO = userService.signUp(signUpDTO);
        return ResponseEntity.ok(savedUserDTO);
    }

    @PostMapping("/sign-in")
    public JWT signIn(@RequestBody SignInDTO signInDTO) {
        String username = signInDTO.getUsername();
        String password = signInDTO.getPassword();
        JWT jwt = userService.signIn(username, password);
        log.info("request username = {}, password = {}", username, password);
        log.info("JWT accessToken = {}, refreshToken = {}", jwt.getAccessToken(), jwt.getRefreshToken());
        return jwt;
    }

    @PostMapping("/test")
    public String test() {
        return SecurityUtil.getCurrentUsername();
    }

    @PostMapping("/{userId}")
}
