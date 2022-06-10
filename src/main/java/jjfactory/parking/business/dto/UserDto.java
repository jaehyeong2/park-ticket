package jjfactory.parking.business.dto;

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
    private String password;
    private String email;
    private String phone;

    public UserDto(User user) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phone = phone;
    }
}
