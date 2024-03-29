package com.app.catchmetable.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Table(name = "users")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DiscriminatorColumn(name="user_arrortment")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_no")
    private Long id;

    @Column(name="user_id")
    private String userId;
    @Column(name="user_pw")
    private String userPw;
    @Column(name="is_use")
    @Enumerated(EnumType.STRING)
    private UserState isUse;


    protected User(String userId, String userPw, UserState isUse) {
        this.userId = userId;
        this.userPw = userPw;
        this.isUse = isUse;
    }
    protected void deleteUser(User user){
        user.isUse = UserState.N;
        updateUser(user);
    }
    protected void updateUser(User user){
        this.userPw = user.userPw;
        this.isUse = user.isUse;
    }
    protected void updateUserPw(String userPw){
        this.userPw = userPw;
    }
}
