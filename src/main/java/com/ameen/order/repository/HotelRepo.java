package com.ameen.order.repository;

import com.ameen.order.model.Hotel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HotelRepo extends MongoRepository<Hotel, Long> {

    Optional<Hotel> findById(String hotelId);

}
