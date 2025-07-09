package com.ameen.order.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "hotel")
public class Hotel {

    @Id
    private String id;
    private String hotelName;

}
