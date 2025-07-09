package com.ameen.order.repository;

import com.ameen.order.model.Orders;
import com.ameen.order.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrdersRepository extends MongoRepository<Orders, Long> {

    List<Orders> findByUser(User user);

    List<Orders> findByDate(LocalDate date);

    Optional<Orders> findById(String orderId);

}
