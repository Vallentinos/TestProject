package com.ezen.controller;

import com.ezen.entity.Heart;
import com.ezen.service.HeartServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Log4j2
public class HeartController {

    @Autowired
    HeartServiceImpl heartService;

    @PostMapping("/heart")
    public void insertHeart(Heart heart) {
        log.info(heart);
        heartService.insertHeart(heart);
    }
}

//    @PostMapping("/heart")
//    public ResponseEntity<String> insertHeart(Heart heart,
//            @SessionAttribute("member") Member member,
//            @RequestParam("recipe_seq") Long recipe_seq) {
//
//        log.info(heart);
//        heartService.insertHeart(heart);
//    }
//}
