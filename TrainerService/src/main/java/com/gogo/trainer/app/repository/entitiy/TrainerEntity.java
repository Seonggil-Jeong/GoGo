package com.gogo.trainer.app.repository.entitiy;

import com.gogo.trainer.enums.RoleType;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "TRAINER")
public class TrainerEntity {
    @Id
    private String trainerId;

    @NonNull
    @Column(name = "TRAINER_PASSWORD", nullable = false)
    private String trainerPassword;

    @NonNull
    @Column(name = "TRAINER_NAME", nullable = false)
    private String trainerName;

    @NonNull
    @Column(name = "TRAINER_PHONE", nullable = false)
    private String trainerPhone;

    @Enumerated(EnumType.STRING)
    @Column(name = "ROLE", nullable = false)
    private RoleType role;

    @NonNull
    @Column(name = "TRAINER_STATE", nullable = false, length = 2)
    private Integer trainerState;

    @OneToMany(mappedBy = "trainer")
    private List<DietCommentEntity> dietCommentEntities;
}
