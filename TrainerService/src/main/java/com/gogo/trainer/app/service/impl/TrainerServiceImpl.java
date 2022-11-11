package com.gogo.trainer.app.service.impl;

import com.gogo.trainer.app.repository.TrainerRepository;
import com.gogo.trainer.app.service.VerifyTrainerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TrainerServiceImpl implements VerifyTrainerService {
    private final TrainerRepository trainerRepository;

    /**
     * find By TrainerId and, isPresent
     * @param trainerId
     * @return
     * @throws Exception
     */
    @Override
    public Boolean isPresentTrainerId(String trainerId) throws Exception {
        return trainerRepository.findById(trainerId).isPresent();
    }
}
