package com.ameen.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Setter
@Getter
public class AdminViewDto {

    private LocalDate date;
    private UserDto user;
    private HotelDto hotel;
    private List<FoodDto> food;
    private List<OrdersDto> order;

}
