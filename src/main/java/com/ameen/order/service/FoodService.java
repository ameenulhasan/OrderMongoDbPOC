package com.ameen.order.service;

import com.ameen.order.dto.AdminViewDto;
import com.ameen.order.dto.FoodCreateDto;
import com.ameen.order.dto.FoodDto;
import com.ameen.order.dto.HotelDto;
import com.ameen.order.dto.OrdersDto;
import com.ameen.order.dto.UserDto;
import com.ameen.order.dto.UserOrdersDto;
import com.ameen.order.model.Food;
import com.ameen.order.response.SuccessResponse;

import java.time.LocalDate;
import java.util.List;

public interface FoodService {

    SuccessResponse<Object> createFoodPrice(FoodDto foodDto);

    SuccessResponse<Object> makeOrder(OrdersDto makeOrderDto);

    SuccessResponse<Object> getAllOrders();

    SuccessResponse<List<UserOrdersDto>> getOrdersByUser(String userId);

    SuccessResponse<Object> deleteOrderById(String orderId);

    SuccessResponse<Object> createFoods(FoodCreateDto foodCreateDto);

    SuccessResponse<Object> createHotel(HotelDto hotelDto);

    SuccessResponse<Object> createUser(UserDto userDto);

    SuccessResponse<List<Food>> getAllFoods();

    SuccessResponse<Food> getFoodById(String id);

    SuccessResponse<List<UserOrdersDto>> filterOrders(String userName, String foodName, LocalDate date);

    SuccessResponse<List<AdminViewDto>> getOrdersByDate(LocalDate date);

    SuccessResponse<Object> getHotelFood(String id);

}
