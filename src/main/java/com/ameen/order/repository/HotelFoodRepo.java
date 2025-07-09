package com.ameen.order.repository;

import com.ameen.order.model.HotelFood;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelFoodRepo extends MongoRepository<HotelFood, Long> {

    Optional<HotelFood> findById(String s);

}
