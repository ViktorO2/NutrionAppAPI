package com.example.NutrionAppvol2.Service;

import com.example.NutrionAppvol2.Entity.Food;
import com.example.NutrionAppvol2.Repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodService {
    private final FoodRepository foodRepository;

@Autowired
    public FoodService(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }
    // Метод за създаване на нов продукт
    public Food createFood(Food food) {
        return foodRepository.save(food);
    }

    // Метод за извличане на всички видове храни
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }

    // Метод за намиране на храна по ID
    public Optional<Food> getFoodById(Long id) {
        return foodRepository.findById(id);
    }

    public Food editFood(Long id, Food food) {
        // Check if the food item with the given ID exists
        Optional<Food> existingFoodOptional = foodRepository.findById(id);
        if (existingFoodOptional.isPresent()) {
            // If the food item exists, update its properties with the new values
            Food existingFood = existingFoodOptional.get();
            existingFood.setName(food.getName());
            existingFood.setDescription(food.getDescription());
            existingFood.setKcal(food.getKcal());
            existingFood.setProtein(food.getProtein());
            existingFood.setFat(food.getFat());
            existingFood.setCarbs(food.getCarbs());
            // Save the updated food item
            return foodRepository.save(existingFood);
        } else {
            // If the food item with the given ID does not exist, return null or throw an exception
            return null;
        }
    }
    public void deleteFoodById(Long id){
    foodRepository.deleteById(id);
    }
    public List<Food> searchFoods(String query) {
        return foodRepository.findByNameContainingIgnoreCase(query);
    }
}

