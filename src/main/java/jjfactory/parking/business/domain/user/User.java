package jjfactory.parking.business.domain.user;

import jjfactory.parking.global.entity.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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

    private String refreshToken;
}
