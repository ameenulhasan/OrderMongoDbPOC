package com.ameen.order.controller;

import com.ameen.order.dto.AdminViewDto;
import com.ameen.order.dto.FoodCreateDto;
import com.ameen.order.dto.FoodDto;
import com.ameen.order.dto.HotelDto;
import com.ameen.order.dto.OrdersDto;
import com.ameen.order.dto.UserDto;
import com.ameen.order.dto.UserOrdersDto;
import com.ameen.order.model.Food;
import com.ameen.order.response.SuccessResponse;
import com.ameen.order.service.FoodService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    private final FoodService foodService;

    public OrderController(FoodService foodService) {
        this.foodService = foodService;
    }

    @PostMapping("/createFoodPrice")
    public SuccessResponse<Object> createFood (@RequestBody FoodDto foodDto){
        return foodService.createFoodPrice(foodDto);
    }

    @PostMapping("/makeOrder")
    public SuccessResponse<Object> makeOrder(@RequestBody OrdersDto orderDto){
        return foodService.makeOrder(orderDto);
    }

    @GetMapping("/getAllOrders")
        public  SuccessResponse<Object> getAllOrders (){
        return foodService.getAllOrders();
    }

    @GetMapping("/getByOrderUser")
    public SuccessResponse<List<UserOrdersDto>> getOrdersByUser(@RequestParam String userId) {
        return foodService.getOrdersByUser(userId);
    }

    @DeleteMapping("/deleteOrderId")
    public SuccessResponse<Object> deleteOrder(@RequestParam String orderId) {
        return foodService.deleteOrderById(orderId);
    }

    @PostMapping("/createFood")
    public SuccessResponse<Object> createFoods (@RequestBody FoodCreateDto foodDto){
        return foodService.createFoods(foodDto);
    }

    @PostMapping("/createHotel")
    public SuccessResponse<Object> createHotel (@RequestBody HotelDto hotelDto){
        return foodService.createHotel(hotelDto);
    }

    @PostMapping("/createUser")
    public SuccessResponse<Object> createUser (@RequestBody UserDto userDto){
        return foodService.createUser(userDto);
    }

    @GetMapping("/getFoods")
    public SuccessResponse<List<Food>> getAllFoods() {
        return foodService.getAllFoods();
    }

    @GetMapping("/getFood")
    public SuccessResponse<Food> getFoodById(@RequestParam String id) {
        return foodService.getFoodById(id);
    }

    @GetMapping("/ordersFilter")
    public SuccessResponse<List<UserOrdersDto>> filterOrders(@RequestParam(required = false) String userName,
                                                             @RequestParam(required = false) String foodName,
                                                             @RequestParam(required = false)
                                                             @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate date) {
        return foodService.filterOrders(userName, foodName, date);
    }

    @GetMapping("/orders")
    public SuccessResponse<List<AdminViewDto>> getOrdersByDate(@RequestParam LocalDate date) {
        return foodService.getOrdersByDate(date);
    }

    @GetMapping("/getHotelFoodById")
    public SuccessResponse<Object> getHotelFoodById(@RequestParam String id) {
        return foodService.getHotelFood(id);
    }

}
