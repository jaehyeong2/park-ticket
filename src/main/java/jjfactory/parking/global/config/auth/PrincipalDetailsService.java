package jjfactory.parking.global.config.auth;

import jjfactory.parking.business.domain.user.User;
import jjfactory.parking.business.repository.user.UserRepository;
import jjfactory.parking.global.handler.ex.BusinessException;
import jjfactory.parking.global.handler.ex.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null) throw new BusinessException(ErrorCode.NOT_FOUND_USER);
        return new PrincipalDetails(user);
    }
}
