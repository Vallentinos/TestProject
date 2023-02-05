package com.ezen.persistence;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ezen.entity.Food;

public interface FoodRepository extends CrudRepository<Food, Long> {

	//List<Food> getFoodList(Food food);
}
