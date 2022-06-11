package jjfactory.parking.business.service.user;

import jjfactory.parking.business.domain.user.User;
import jjfactory.parking.business.dto.user.UserDto;
import jjfactory.parking.business.repository.user.UserRepository;
import jjfactory.parking.global.handler.ex.BusinessException;
import jjfactory.parking.global.handler.ex.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Transactional
    public String signUp(UserDto dto){
        duplicateCheck(dto.getUsername());
        String encPassword = encode(dto.getPassword());
        User user = User.create(dto, encPassword);

        userRepository.save(user);
        return "Y";
    }

    private void duplicateCheck(String username) {
        User user = userRepository.findByUsername(username);
        if(user != null) throw new BusinessException(ErrorCode.DUPLICATE_LOGIN_ID);
    }

    private String encode(String rawPassword) {
        String encPassword = encoder.encode(rawPassword);
        return encPassword;
    }
}
