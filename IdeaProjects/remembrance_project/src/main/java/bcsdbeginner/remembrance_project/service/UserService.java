package bcsdbeginner.remembrance_project.service;

import bcsdbeginner.remembrance_project.DTO.SignUpDTO;
import bcsdbeginner.remembrance_project.DTO.UserDTO;
import bcsdbeginner.remembrance_project.domain.JWT;
import org.springframework.transaction.annotation.Transactional;

public interface UserService {
    @Transactional
    UserDTO signUp(SignUpDTO signUpDTO);

    @Transactional
    JWT signIn(String username, String password);
}
