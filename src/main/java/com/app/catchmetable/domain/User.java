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
@DiscriminatorColumn(name="user_arrortment") // 자식 엔티티에 구분할 칼럼명 명시
@Inheritance(strategy = InheritanceType.JOINED) // 해당 필드의 상속 전략을 설정. JOINED로 설정시 해당 엔티티를 슈퍼타입 테이블(기본)으로 만들고 이를 상속받은 엔티티는 서브타입테이블 방식으로 join하는 방식
public abstract class User extends BaseEntity {
    @Id // PK 명시
    @GeneratedValue(strategy = GenerationType.IDENTITY) //자동 키 생성. 방법은 해당 DB에 위임. 이번에 쓰는 DB는 MySQL이니 Auto Increments
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
