package com.example.NutrionAppvol2.Controllers;

import com.example.NutrionAppvol2.Service.FoodService;
import com.example.NutrionAppvol2.Entity.Food;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/foods")
@CrossOrigin(origins = "http://localhost:3000")
public class FoodController {
    private final FoodService foodService;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping
    public ResponseEntity<List<Food>> getAllFoods() {
        List<Food> foods = foodService.getAllFoods();
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Food> getFoodById(@PathVariable Long id) {
        Food food = foodService.getFoodById(id).orElse(null);
        if (food != null) {
            return new ResponseEntity<>(food, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Food> createFood(@RequestBody Food food) {
        Food createdFood = foodService.createFood(food);
        return new ResponseEntity<>(createdFood, HttpStatus.CREATED);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Food> updateFood(@PathVariable Long id, @RequestBody Food food) {
        Food updatedFood = foodService.editFood(id, food);
        return new ResponseEntity<>(updatedFood, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Food> deleteFood(@PathVariable Long id) {
        foodService.deleteFoodById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Food>> searchFood(@RequestParam String query) {
        List<Food> foods = foodService.searchFoods(query);
        return new ResponseEntity<>(foods, HttpStatus.OK);
    }
}
