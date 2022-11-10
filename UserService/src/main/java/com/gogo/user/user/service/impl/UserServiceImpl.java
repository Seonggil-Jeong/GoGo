package com.gogo.user.user.service.impl;

import com.gogo.user.enums.RoleType;
import com.gogo.user.exceptions.UserException;
import com.gogo.user.exceptions.result.UserExceptionResult;
import com.gogo.user.repository.UserRepository;
import com.gogo.user.repository.entity.UserEntity;
import com.gogo.user.security.impl.AccessTokenProvider;
import com.gogo.user.security.token.AccessToken;
import com.gogo.user.security.token.PasswordAuthenticationToken;
import com.gogo.user.user.service.AuthService;
import com.gogo.user.user.service.UserService;
import com.gogo.user.user.service.VarifyUserService;
import com.gogo.user.user.vo.LoginUserRequest;
import com.gogo.user.user.vo.RegisterUserRequest;
import com.gogo.user.user.vo.UpdateUserRequest;
import com.gogo.user.user.vo.UserInfoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements VarifyUserService, AuthService, UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AccessTokenProvider tokenProvider;
    private final AuthenticationManager authenticationManager;

    /**
     * for UserService ---------------------------------------------------------------------------
     */
    @Override
    public UserInfoResponse findUserInfo(final String userId) throws Exception {
        final UserEntity user = userRepository.findById(userId).orElseThrow(()
                -> new UserException(UserExceptionResult.USER_NOT_FOUND));

        return UserInfoResponse.builder()
                .userId(user.getUserId())
                .userNickname(user.getUserNickname())
                .userName(user.getUserName())
                .trainerId(user.getTrainerId()).build();
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public UserInfoResponse updateUserInfo(String userId, UpdateUserRequest request) throws Exception {
        final UserEntity user = userRepository.findById(userId).orElseThrow(()
                -> new UserException(UserExceptionResult.USER_NOT_FOUND));

        if (!canUpdateUserNickname(userId, request.getUserNickname())) {
            throw new UserException(UserExceptionResult.DUPLICATED_USER_NICKNAME);
        }

        final UserEntity result = userRepository.save(UserEntity.builder()
                .userId(user.getUserId())
                .userPassword(user.getUserPassword())
                .role(user.getRole())
                .userState(user.getUserState())
                .userNickname(request.getUserNickname())
                .userName(request.getUserName())
                .trainerId(request.getTrainerId()).build());

        return UserInfoResponse.builder()
                .userId(result.getUserId())
                .trainerId(result.getTrainerId())
                .userName(result.getUserName())
                .userNickname(result.getUserNickname()).build();
    }

    private boolean canUpdateUserNickname(final String userId, final String userNickname) throws Exception {
        final Optional<UserEntity> result = userRepository.findByUserNickname(userNickname);

        if (result.isEmpty()) {
            return true;
        }

        return result.get().getUserId().equals(userId);

    }

    /**
     * for AuthService ---------------------------------------------------------------------------
     */

    /**
     * User Login Service
     *
     * @param request {userId, userPassword}
     * @return token
     * @throws Exception
     */
    @Override
    public String userLogin(LoginUserRequest request) throws Exception {
        Authentication authentication = authenticationManager.authenticate(
                new PasswordAuthenticationToken(request.getUserId(), request.getUserPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return createToken((PasswordAuthenticationToken) authentication);
    }

    /**
     * Create Token
     *
     * @param token
     * @return (String) token
     */
    private String createToken(final PasswordAuthenticationToken token) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("userId", token.getUserId());
        claims.put("role", token.getRole());

        final AccessToken accessToken = tokenProvider.createToken(
                token.getPrincipal().toString(),
                token.getAuthorities().iterator().next().getAuthority(),
                claims,
                Date.from(LocalDateTime.now().plusMinutes(30).atZone(ZoneId.systemDefault()).toInstant())
        );

        return accessToken.getToken();
    }


    @Override
    @Transactional(rollbackOn = Exception.class)
    public void userRegister(RegisterUserRequest request) throws Exception {

        userRepository.save(UserEntity.builder()
                // validate check
                .userId(getValidateUserId(request.getUserId()))
                .userNickname(getValidateUserNickname(request.getUserNickname()))
                .trainerId(getValidateTrainerId(request.getTrainerId()))

                .userName(request.getUserName())
                .userPassword(passwordEncoder.encode(request.getUserPassword()))
                // default
                .userState(1)
                .role(RoleType.USER).build());

    }


    /**
     * for VarifyService ---------------------------------------------------------------------------
     */

    /**
     * 사용자 아이디 중복 확인 후 pass -> return userId
     *
     * @param userId
     * @return
     * @throws Exception
     */
    @Override
    public String getValidateUserId(final String userId) throws Exception {
        final Optional<UserEntity> result = userRepository.findById(userId);

        if (result.isPresent()) {
            throw new UserException(UserExceptionResult.DUPLICATED_USER_ID);
        } else {
            return userId;
        }

    }

    /**
     * TrainerService에서 유효한 trainerId 인지 확인 pass -> return trainerId
     *
     * @param trainerId
     * @return
     * @throws Exception
     */
    @Override
    public String getValidateTrainerId(final String trainerId) throws Exception {
        if (trainerId.isEmpty()) {
            return "";
        } else {
            // check in trainerService
        }
        // TODO: 2022-11-10 Add Method
        return trainerId;
    }

    /**
     * 사용자 닉네임 중복 확인 후 유요한 닉네임일 경우 닉네임 리턴
     *
     * @param userNickname
     * @return
     * @throws Exception
     */
    @Override
    public String getValidateUserNickname(String userNickname) throws Exception {
        final Optional<UserEntity> result = userRepository.findByUserNickname(userNickname);

        if (result.isPresent()) {
            throw new UserException(UserExceptionResult.DUPLICATED_USER_NICKNAME);
        } else {
            return userNickname;
        }
    }
}
