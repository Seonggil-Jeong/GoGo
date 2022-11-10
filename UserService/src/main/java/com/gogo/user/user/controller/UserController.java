package com.gogo.user.user.controller;

import com.gogo.user.security.AuthTokenProvider;
import com.gogo.user.security.impl.AccessTokenProvider;
import com.gogo.user.security.token.AccessToken;
import com.gogo.user.user.service.UserService;
import com.gogo.user.user.vo.LoginUserRequest;
import com.gogo.user.user.vo.UpdateUserRequest;
import com.gogo.user.user.vo.UserInfoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

import static com.gogo.user.constants.UserConstants.AUTHORIZATION_TOKEN_KEY;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
@Tag(name = "사용자 정보 Controller", description = "사용자 정보 Controller")
public class UserController {
    private final UserService userService;
    private final AccessTokenProvider accessTokenProvider;


    @Operation(summary = "사용자 정보 조회", description = "사용자 정보 조회", tags = {"user"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "사용자 로그인 성공"),
    })
    @GetMapping("/users")
    public ResponseEntity<UserInfoResponse> findUserInfoByToken(
            @RequestHeader(AUTHORIZATION_TOKEN_KEY) final String token) throws Exception {

        return ResponseEntity.ok().body(userService.findUserInfo(accessTokenProvider.getUserId(token)));
    }

    @Operation(summary = "사용자 정보 수정", description = "사용자 정보 수정", tags = {"user"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "사용자 정보 수정 완료")
    })
    @PatchMapping("/users")
    public ResponseEntity<UserInfoResponse> updateUser(
            @RequestHeader(AUTHORIZATION_TOKEN_KEY) final String token,
            @RequestBody final UpdateUserRequest request) throws Exception {

        return ResponseEntity.ok().body(userService.updateUserInfo(accessTokenProvider.getUserId(token), request));
    }
}
