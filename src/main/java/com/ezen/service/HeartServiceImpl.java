package com.ezen.service;

import com.ezen.entity.Heart;
import com.ezen.persistence.HeartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HeartServiceImpl implements HeartService{

    @Autowired
    private HeartRepository heartRepository;

    @Override
    public void insertHeart(Heart heart) {
        heartRepository.save(heart);
    }

    @Override
    public void deleteHeart(Heart heart) {
        heartRepository.deleteById(heart.getHeartSeq());
    }
}
