package jjfactory.parking.business.dto.user.res;

import jjfactory.parking.business.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserResponse {
    private String name;
    private String username;
    private String password;
    private String email;
    private String phone;

    public UserResponse(User user) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
}
