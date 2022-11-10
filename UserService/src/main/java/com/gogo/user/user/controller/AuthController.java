package com.gogo.user.user.controller;

import com.gogo.user.user.service.AuthService;
import com.gogo.user.user.vo.LoginUserRequest;
import com.gogo.user.user.vo.RegisterUserRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

import static com.gogo.user.constants.AuthConstants.AUTHORIZATION_TOKEN_KEY;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@Tag(name = "사용자 인증 Controller", description = "사용자 로그인 및 회원가입")
public class AuthController {
    private final AuthService userService;

    @Operation(summary = "사용자 로그인", description = "사용자 로그인", tags = {"user"})
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "사용자 로그인 성공, create Token"),
            @ApiResponse(responseCode = "404", description = "일치하는 사용자 정보를 찾을 수 없음"),
            @ApiResponse(responseCode = "400", description = "사용자 비밀번호 불일치"),
            @ApiResponse(responseCode = "400", description = "탈퇴한 사용자")
    })
    @PostMapping("/login")
    public ResponseEntity<Void> loginUser(
            @RequestBody @Validated final LoginUserRequest request, HttpServletResponse response) throws Exception {
        response.setHeader(AUTHORIZATION_TOKEN_KEY, userService.userLogin(request));
        return ResponseEntity.ok().build();
    }


    @Operation(summary = "사용자 회원가입", description = "사용자 회원가입", tags = {"user"})
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "사용자 회원가입 성공"),
            @ApiResponse(responseCode = "400", description = "Bad Request")
    })
    @PostMapping("/register")
    public ResponseEntity<Void> registerUser(
            @RequestBody @Validated RegisterUserRequest request) throws Exception {
        userService.userRegister(request);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
