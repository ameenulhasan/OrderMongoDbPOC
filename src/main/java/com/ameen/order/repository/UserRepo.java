package com.ameen.order.repository;

import com.ameen.order.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends MongoRepository<User, Long> {

    Optional<User> findById(String userId);

    Optional<User> findByName(String userId);

}
