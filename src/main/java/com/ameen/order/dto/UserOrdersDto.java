package com.ameen.order.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserOrdersDto {

    private String id;
    private String userName;
    private String foodName;
    private String quantity;
    private String price;

}
