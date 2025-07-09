package com.ameen.order.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Getter
@Setter
@Document(collection ="hotel_food")
public class HotelFood {

    @Id
    private String id;
    private Long price;
    @DocumentReference
    private Food foodId;
    @DocumentReference
    private Hotel hotelId;

}

