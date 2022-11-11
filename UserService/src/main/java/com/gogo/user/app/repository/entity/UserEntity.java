package com.gogo.user.app.repository.entity;

import com.gogo.user.enums.RoleType;
import lombok.*;

import javax.persistence.*;
import java.util.List;
@Getter
@Builder
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "USER_INFO")
public class UserEntity {
    @Id
    @Column(name = "USER_ID")
    private String userId;

    @NonNull
    @Column(name = "USER_PASSWORD", nullable = false)
    private String userPassword;

    @NonNull
    @Column(name = "USER_NAME", nullable = false)
    private String userName;

    @NonNull
    @Column(name = "USER_NICKNAME", nullable = false)
    private String userNickname;

    @Column(name = "TRAINER_ID")
    private String trainerId;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false)
    private RoleType role;

    @NonNull
    @Column(name = "USER_STATE", nullable = false, length = 2)
    private Integer userState; // 1 or 0

    @OneToMany(mappedBy = "user")
    List<DietEntity> diets;

}
