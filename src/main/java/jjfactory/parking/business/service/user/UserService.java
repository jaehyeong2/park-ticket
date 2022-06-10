package jjfactory.parking.business.service.user;

import jjfactory.parking.business.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {
    private UserRepository userRepository;
}
