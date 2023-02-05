package com.ezen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FoodController {

    @GetMapping("/foodList")
    public String foodList() {
        return "food/foodList";
    }

    @GetMapping("/recipeList")
    public String recipeList() {
        return "recipe/recipeList";
    }
}
