package com.gogo.trainer.app.repository.entitiy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "DIET_COMMENT", indexes = {
        @Index(name = "IU_DIET_COMMENT_01", columnList = "DIET_ID")
})
@ToString(exclude = {"trainer"})
public class DietCommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dietCommentSeq;

    @NonNull
    @Column(name = "DIET_ID", nullable = false)
    private UUID dietId; // diet UUID

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private TrainerEntity trainer;
}
