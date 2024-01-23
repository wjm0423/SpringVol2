package bcsdbeginner.remembrance_project.DTO;

import bcsdbeginner.remembrance_project.domain.User;
import lombok.*;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    private String username;
    private String department;
    private Long grade;
    private String registrable_grade;

    static public UserDTO toDTO(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .department(user.getDepartment())
                .grade(user.getGrade())
                .registrable_grade(user.getRegistrable_grade())
                .build();
    }

    public User toEntity() {
        return User.builder()
                .id(id)
                .username(username)
                .department(department)
                .grade(grade)
                .registrable_grade(registrable_grade)
                .build();
    }
}
