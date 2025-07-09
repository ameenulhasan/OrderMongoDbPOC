package com.ameen.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FoodDto {

    private String hotelId;
    private List<String> foods;
    private List<String> price;

}