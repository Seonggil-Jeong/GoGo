package com.gogo.diet.trainer.service;

import com.gogo.diet.enums.RoleType;
import com.gogo.diet.service.DietService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class TrainerDietService implements DietService {
    @Override
    public boolean support(final String role) {
        return RoleType.TRAINER.getValue().equals(role);
    }
}
