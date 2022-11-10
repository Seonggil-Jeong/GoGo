package com.gogo.diet.user.service;

import com.gogo.diet.enums.RoleType;
import com.gogo.diet.service.DietService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDietService implements DietService {
    @Override
    public boolean support(String role) {
        return RoleType.USER.getValue().equals(role);
    }
}
