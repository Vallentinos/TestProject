package com.ezen.test;

import com.ezen.entity.Board;
import com.ezen.entity.Heart;
import com.ezen.entity.Member;
import com.ezen.entity.Recipe;
import com.ezen.persistence.HeartRepository;
import com.ezen.service.HeartService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class HeartRepositoryTest {

    @Autowired
    HeartRepository heartRepository;
    @Autowired
    HeartService heartService;

    @Test
    void insertHeart() {

        Recipe recipe = new Recipe();
        Member member = new Member();
        member.setUsername("member12");
        recipe.setRecipe_seq(1L);

        Heart heart = new Heart();
        heart.setMember(member);
        heart.setRecipe(recipe);

        heartRepository.save(heart);
    }
}
