package com.ameen.order.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HotelFoodListDto {

    private String id;
    private String hotelName;
    private String foods;
    private Long price;

}
