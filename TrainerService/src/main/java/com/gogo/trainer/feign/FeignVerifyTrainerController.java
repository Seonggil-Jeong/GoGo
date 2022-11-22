package com.gogo.trainer.feign;

import com.gogo.trainer.app.service.VerifyTrainerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/client/verify")
public class FeignVerifyTrainerController {
    private final VerifyTrainerService verifyTrainerService;


    @GetMapping("/trainer-id")
    public ResponseEntity<Boolean> isPresentTrainerId(
            @RequestParam(value = "trainer-id", required = true) final String trainerId) throws Exception {

        return ResponseEntity.ok().body(verifyTrainerService.isPresentTrainerId(trainerId));
    }
}
