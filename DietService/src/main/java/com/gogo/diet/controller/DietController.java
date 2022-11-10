package com.gogo.diet.controller;

import com.gogo.diet.security.provider.AccessTokenProvider;
import com.gogo.diet.service.DietService;
import com.gogo.diet.service.DietServiceFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.gogo.diet.constants.AuthConstants.AUTHORIZATION_TOKEN_KEY;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class DietController {
    private final DietServiceFactory dietServiceFactory;
    private final AccessTokenProvider tokenProvider;

    @GetMapping("/diets")
    public ResponseEntity<Void> getDietList(@RequestHeader(AUTHORIZATION_TOKEN_KEY) final String token) throws Exception {
        final DietService dietService = dietServiceFactory.findServiceImpl(tokenProvider.getRole(token));

        return null;
    }
}
