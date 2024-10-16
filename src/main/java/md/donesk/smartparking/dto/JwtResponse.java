package md.donesk.smartparking.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    @Getter
    @Setter
    private Long id;
    @Getter
    @Setter
    private String email;
    @Getter
    private final List<? extends GrantedAuthority> roles;

    public JwtResponse(String accessToken, Long id, String email, List<? extends GrantedAuthority> roles) {
        this.token = accessToken;
        this.id = id;
        this.email = email;
        this.roles = roles;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

}