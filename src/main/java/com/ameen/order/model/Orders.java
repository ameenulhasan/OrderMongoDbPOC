package com.ameen.order.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDate;

@Getter
@Setter
@Document(collection ="orders")
public class Orders {

    @Id
    private String id;
    private LocalDate date = LocalDate.now();
    private Long quantity;
    @DocumentReference
    private HotelFood hotelFood;
    @DocumentReference
    private User user;

}
