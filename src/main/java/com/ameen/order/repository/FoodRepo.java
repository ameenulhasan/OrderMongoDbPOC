package com.ameen.order.repository;

import com.ameen.order.model.Food;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FoodRepo extends MongoRepository<Food, Long> {

    Optional<Food> findById(String id);

}
