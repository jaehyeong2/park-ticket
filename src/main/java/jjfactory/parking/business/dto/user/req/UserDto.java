package jjfactory.parking.business.dto.user.req;

import jjfactory.parking.business.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserDto {
    private String name;
    private String username;
    private String nickname;
    private String password;
    private String email;
    private String phone;

    public UserDto(User user) {
        this.name = name;
        this.username = username;
        this.nickname = nickname;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
}
