package com.ameen.order.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ViewDto {

    private LocalDate date;
    private String username;
    private String hotelName;
    private String foodName;
    private String quantity;
    private String priceItems;

}
