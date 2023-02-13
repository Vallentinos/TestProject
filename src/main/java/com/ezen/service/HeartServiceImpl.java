package com.ezen.service;

import com.ezen.entity.Heart;
import com.ezen.persistence.HeartRepository;
import com.ezen.persistence.RecipeRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Log4j2
public class HeartServiceImpl implements HeartService{

    @Autowired
    private HeartRepository heartRepository;
    @Autowired
    private RecipeRepository recipeRepository;

    @Transactional
    @Override
    public int insertHeart(Heart heart) {
        Optional<Heart> findHeart = heartRepository.getHeart(
                heart.getRecipe().getRecipe_seq(), heart.getMember().getUsername());
//        int good = findHeart.get().getRecipe().getGood()+1;
        log.info("insertHeart()", heart);

        if (findHeart.isEmpty()) {
            heartRepository.save(heart); // 좋아요가 없으면 저장
            return 1;
        } else {
            heartRepository.deleteById(findHeart.get().getHeartSeq());
            return 0;
        }
    }

    @Override
    public int getHeart(Heart heart) {
        Optional<Heart> findHeart = heartRepository.getHeart(
                heart.getRecipe().getRecipe_seq(), heart.getMember().getUsername());
        log.info("getHeart" + findHeart);
        // 좋아요가 없으면 0, 있으면 1
        if (findHeart.isEmpty()) {
            return 0;
        } else {
            return 1;
        }
    }


}
