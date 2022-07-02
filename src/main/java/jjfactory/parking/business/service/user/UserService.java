package jjfactory.parking.business.service.user;

import jjfactory.parking.business.domain.user.User;
import jjfactory.parking.business.dto.user.res.UserResponse;
import jjfactory.parking.business.repository.user.UserRepository;
import jjfactory.parking.global.handler.ex.BusinessException;
import jjfactory.parking.global.handler.ex.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {
    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserResponse findUserById(Long id){
        User user = getUser(id);
        return new UserResponse(user);
    }

    @Transactional(readOnly = true)
    public Page<UserResponse> findUsers(Pageable pageable){
        Page<User> users = userRepository.findAll(pageable);
        List<UserResponse> userResponses = users.stream().map(u -> new UserResponse(u)).collect(Collectors.toList());
        return new PageImpl<>(userResponses,pageable, users.getSize());
    }

    public String deleteUser(Long id){
        User user = getUser(id);
        user.deleteUser();
        return "Y";
    }

    @Transactional(readOnly = true)
    public String checkDuplicateNickname(String nickname){
        User user = userRepository.findByNickname(nickname);
        if (user != null) throw new BusinessException(ErrorCode.DUPLICATE_NICKNAME);
        return "Y";
    }

    private User getUser(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> {
            throw new NoSuchElementException("조회실패");
        });
        return user;
    }
}
