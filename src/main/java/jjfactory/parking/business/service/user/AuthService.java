package jjfactory.parking.business.service.user;

import jjfactory.parking.business.domain.user.User;
import jjfactory.parking.business.dto.user.req.LoginRequest;
import jjfactory.parking.business.dto.user.req.UserDto;
import jjfactory.parking.business.dto.user.res.LoginResponse;
import jjfactory.parking.business.repository.user.UserRepository;
import jjfactory.parking.global.config.auth.JwtTokenProvider;
import jjfactory.parking.global.handler.ex.BusinessException;
import jjfactory.parking.global.handler.ex.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtTokenProvider tokenProvider;

    @Transactional
    public String signUp(UserDto dto){
        duplicateCheck(dto.getUsername());
        String encPassword = encode(dto.getPassword());
        User user = User.create(dto, encPassword);

        userRepository.save(user);
        return "Y";
    }

    public LoginResponse login(LoginRequest dto){
        User user = userRepository.findByUsername(dto.getUsername());
        matchPassword(dto.getPassword(),user.getPassword());
        String token = createToken(user);
        String refreshToken = createRefreshToken();
        return new LoginResponse(token,refreshToken);
    }

    private void matchPassword(String reqPassword, String savedPassword) {
        boolean result = encoder.matches(reqPassword, savedPassword);
        if(! result) throw new BusinessException(ErrorCode.NOT_MATCH_PASSWORD);
    }

    public void duplicateCheck(String username) {
        User user = userRepository.findByUsername(username);
        if(user != null) throw new BusinessException(ErrorCode.DUPLICATE_LOGIN_ID);
    }

    private String encode(String rawPassword) {
        String encPassword = encoder.encode(rawPassword);
        return encPassword;
    }

    public String createToken(User user){
        return tokenProvider.createToken(user.getUsername());
    }

    public String createRefreshToken(){
        return tokenProvider.createRefreshToken();
    }
}
