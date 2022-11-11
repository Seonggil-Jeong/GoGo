package com.gogo.trainer.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {
    TRAINER("ROLE_TRAINER"),
    ;

    private final String value;
}
