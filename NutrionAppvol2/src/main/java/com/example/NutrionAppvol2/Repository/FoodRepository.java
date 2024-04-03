package com.example.NutrionAppvol2.Repository;

import com.example.NutrionAppvol2.Entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface    FoodRepository extends JpaRepository<Food, Long> {
   List<Food> findByNameContainingIgnoreCase(String name);
}
