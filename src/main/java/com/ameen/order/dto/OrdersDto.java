package com.ameen.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrdersDto {

    private List<String> hotelFoodId;
    private List<String> quantity;
    private String userId;

}
