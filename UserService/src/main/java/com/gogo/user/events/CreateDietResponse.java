package com.gogo.user.events;

import com.gogo.user.enums.SlotType;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Getter
@Builder
@RequiredArgsConstructor
public class CreateDietResponse {
    // from UserEntity
    private final String userId;
    private final String trainerId;

    // from DietEntity
    private final UUID dietId;
    private final String dietContent; // user Comment
    private final String dietImagePath;
    private final SlotType slot; // BREAKFAST, LUNCH, DINNER
    private final Date createAt;
}
