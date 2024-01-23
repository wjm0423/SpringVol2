package bcsdbeginner.remembrance_project.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@AllArgsConstructor
public class JWT {
    private String grantType;
    private String accessToken;
    private String refreshToken;
}
