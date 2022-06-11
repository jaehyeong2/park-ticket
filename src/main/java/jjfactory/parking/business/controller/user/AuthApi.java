package jjfactory.parking.business.controller.user;


import jjfactory.parking.business.dto.user.UserDto;
import jjfactory.parking.business.service.user.AuthService;
import jjfactory.parking.global.dto.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/auth")
@RestController
public class AuthApi {
    private final AuthService authService;

    @PostMapping("/signup")
    public ApiResponse<String> signUp(@RequestBody UserDto dto){
        return new ApiResponse<>(authService.signUp(dto));
    }
}
