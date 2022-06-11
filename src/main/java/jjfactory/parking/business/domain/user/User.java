package jjfactory.parking.business.domain.user;

import jjfactory.parking.business.dto.user.UserDto;
import jjfactory.parking.global.entity.BaseTimeEntity;
import jjfactory.parking.global.entity.DeleteStatus;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class User extends BaseTimeEntity {
    @Id @GeneratedValue
    private Long id;

    private String name;
    private String username;
    private String password;
    private String phone;
    private String email;

    @ElementCollection(fetch = FetchType.LAZY) //이거 없으면 에러남
    @Enumerated(EnumType.STRING)
    private List<Role> roles = new ArrayList<>();

    private String refreshToken;

    @Enumerated(EnumType.STRING)
    private DeleteStatus deleteStatus;

    @Builder
    public User(String name, String username, String password, String phone, String email, List<Role> roles, String refreshToken, DeleteStatus deleteStatus) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.roles = roles;
        this.refreshToken = refreshToken;
        this.deleteStatus = deleteStatus;
    }

    public static User create(UserDto dto, String password){
        return builder()
                .username(dto.getUsername())
                .name(dto.getName())
                .deleteStatus(DeleteStatus.NON_DELETED)
                .phone(dto.getPhone())
                .password(password)
                .email(dto.getEmail())
                .refreshToken("")
                .roles(Collections.singletonList(Role.ROLE_NORMAL))
                .build();
    }

    public void deleteUser() {
        this.deleteStatus = DeleteStatus.DELETED;
    }
}
