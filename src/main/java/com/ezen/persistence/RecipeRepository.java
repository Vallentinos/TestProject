package com.ezen.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ezen.entity.Recipe;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
	
	//List<Recipe> getRecipeList(Recipe recipe);
}
