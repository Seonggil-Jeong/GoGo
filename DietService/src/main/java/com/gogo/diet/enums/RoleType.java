package com.gogo.diet.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoleType {
    USER("ROLE_USER"),
    TRAINER("ROLE_TRAINER")
    ;

    private final String value;

}
