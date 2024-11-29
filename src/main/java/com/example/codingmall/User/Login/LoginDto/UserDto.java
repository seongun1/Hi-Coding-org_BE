package com.example.codingmall.User.Login.LoginDto;

import com.example.codingmall.User.Role;
import com.example.codingmall.User.User;
import com.example.codingmall.User.UserStatus;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class UserDto {
    private Long id;           // 회원고유번호
    private String username;   // 아이디
    private String password;   // 비밀번호
    private int birth;         // 주민등록번호
    private String name;       // 사용자 이름
    private String phoneNumber;// 휴대폰 번호
    private UserStatus status = UserStatus.ACTIVATE;   // 상태(탈퇴 회원 여부 파악)
    private Role role = Role.ROLE_USER;                // 역할
    private String email;      // 이메일

    // UserDto -> User 변환 메서드
    public User toEntity() {
        return User.builder()
                .id(this.id)
                .username(this.username)
                .password(this.password)
                .birth(this.birth)
                .name(this.name)
                .phoneNumber(this.phoneNumber)
                .status(this.status)
                .role(this.role)
                .email(this.email)
                .build();
    }
}
