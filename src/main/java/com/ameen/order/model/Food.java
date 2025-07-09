package com.ameen.order.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "food")
public class Food {

    @Id
    private String id;
    private String foodName;

}
