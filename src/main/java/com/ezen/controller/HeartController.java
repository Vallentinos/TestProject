package com.ezen.controller;

import com.ezen.entity.Heart;
import com.ezen.entity.Member;
import com.ezen.entity.Recipe;
import com.ezen.service.HeartService;
import com.ezen.service.HeartServiceImpl;
import com.ezen.service.RecipeService;
import com.ezen.service.RecipeServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;



@RestController
@Log4j2
public class HeartController {

    @Autowired
    HeartService heartService;
    @Autowired
    RecipeService recipeService;

    @PostMapping("/heart")
    public @ResponseBody int insertHeart(@RequestBody Map<String, String> map) {
        System.out.println("사용자명: "+map.get("username"));
        System.out.println("레시피 Seq: " + map.get("recipe_seq"));
        System.out.println("좋아요 수: " + map.get("good"));

        Member member = new Member();
        member.setUsername(map.get("username"));
        Recipe recipe = new Recipe();
        recipe.setRecipe_seq(Long.parseLong(map.get("recipe_seq")));
        recipe.setGood(Integer.parseInt(map.get("good")));

        Heart heart = new Heart();
        heart.setMember(member);
        heart.setRecipe(recipe);

        log.info(heart);
        int heart_val = heartService.insertHeart(heart);

        return heart_val;
    }
}
