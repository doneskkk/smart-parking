package md.donesk.smartparking.dto;

import lombok.Builder;
import lombok.Data;
import md.donesk.smartparking.model.User;

@Data
@Builder
public class UserResponse {
    private Long id;
    private String name;
    private String email;
    private String phone;

    public static UserResponse convertToUserResponse(User user) {
        return UserResponse.builder().name(user.getName())
                .phone(user.getPhone())
                .email(user.getEmail())
                .id(user.getId()).build();
    }
}
