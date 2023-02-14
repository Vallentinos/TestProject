package com.ezen.service;

import com.ezen.entity.Heart;
import com.ezen.entity.Recipe;
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
    private RecipeService recipeService;

    @Transactional
    @Override
    public int insertHeart(Heart heart) {
        Optional<Heart> findHeart = heartRepository.getHeart(
                heart.getRecipe().getRecipe_seq(), heart.getMember().getUsername());
        log.info("insertHeart()", heart);

        if (findHeart.isEmpty()) {
            heart.getRecipe().setGood(+1);
            heartRepository.save(heart); // 좋아요가 없으면 저장
            return 1;
        } else {
            findHeart.get().getRecipe().setGood(-1);
            heartRepository.deleteById(findHeart.get().getHeartSeq()); // 좋아요가 있으면 삭제
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
