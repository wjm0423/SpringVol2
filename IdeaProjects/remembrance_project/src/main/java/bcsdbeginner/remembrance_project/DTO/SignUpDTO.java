package bcsdbeginner.remembrance_project.DTO;

import bcsdbeginner.remembrance_project.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpDTO {
    private String username;
    private String password;
    private String department;
    private Long grade;
    private String registrable_grade;
    private List<String> roles = new ArrayList<>();

    public User toEntity(String encodedPassword, List<String> roles) {
        return User.builder()
                .username(username)
                .password(encodedPassword)
                .department(department)
                .grade(grade)
                .registrable_grade(registrable_grade)
                .roles(roles)
                .build();
    }
}
