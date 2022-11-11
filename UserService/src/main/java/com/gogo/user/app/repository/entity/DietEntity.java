package com.gogo.user.app.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gogo.user.enums.SlotType;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "DIET")
@ToString(exclude = {"user"})
public class DietEntity {
    @Id
    private UUID dietId;

    @Column(name = "DIET_CONTENT")
    private String dietContent; // user Comment

    @NonNull
    @Column(name = "DIET_IMAGE_PATH", nullable = false)
    private String dietImagePath;

    @NonNull
    @Enumerated(EnumType.STRING)
    @Column(name = "SLOT", nullable = false)
    private SlotType slot; // BREAKFAST, LUNCH, DINNER



    @NonNull
    @Column(name = "CREATE_AT", nullable = false)
    private Date createAt;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private UserEntity user;


}
