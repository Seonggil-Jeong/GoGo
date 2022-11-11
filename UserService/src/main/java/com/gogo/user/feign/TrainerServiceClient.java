package com.gogo.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "TRAINER-SERVICE")
public interface TrainerServiceClient {

    @GetMapping("/client/verify/trainer-id")
    ResponseEntity<Boolean> isPresentTrainerId(
            @RequestParam(value = "trainer-id", required = true) final String trainerId) throws Exception;


}
