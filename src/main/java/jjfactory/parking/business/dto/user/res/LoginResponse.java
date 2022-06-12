package jjfactory.parking.business.dto.user.res;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class LoginResponse {
    private String token;
    private String refreshToken;
}
