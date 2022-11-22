package com.gogo.user.event;

import com.gogo.user.enums.SlotType;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

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
