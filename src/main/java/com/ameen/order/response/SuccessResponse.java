package com.ameen.order.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SuccessResponse<T> {

    private String message;
    private T data;
    private Boolean success;

}
