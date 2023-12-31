package bcsd.BoardAPI.domain;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "userName", nullable = false)
    private String userName;

    @Column(name = "userPw", nullable = false)
    private String userPw;

    @Builder
    public User(Long userId, String userName, String userPw) {
        this.userId = userId;
        this.userName = userName;
        this.userPw = userPw;
    }
}
