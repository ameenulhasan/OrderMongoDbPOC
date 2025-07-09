package com.ameen.order.serviceImpl;

import com.ameen.order.dto.AdminViewDto;
import com.ameen.order.dto.FoodCreateDto;
import com.ameen.order.dto.FoodDto;
import com.ameen.order.dto.HotelDto;
import com.ameen.order.dto.HotelFoodListDto;
import com.ameen.order.dto.OrdersDto;
import com.ameen.order.dto.UserDto;
import com.ameen.order.dto.UserOrdersDto;
import com.ameen.order.dto.ViewDto;
import com.ameen.order.model.Food;
import com.ameen.order.model.Hotel;
import com.ameen.order.model.HotelFood;
import com.ameen.order.model.Orders;
import com.ameen.order.model.User;
import com.ameen.order.repository.FoodRepo;
import com.ameen.order.repository.HotelFoodRepo;
import com.ameen.order.repository.HotelRepo;
import com.ameen.order.repository.OrdersRepository;
import com.ameen.order.repository.UserRepo;
import com.ameen.order.response.SuccessResponse;
import com.ameen.order.service.FoodService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoodServiceImpl implements FoodService {

    private final HotelFoodRepo hotelFoodRepo;
    private final OrdersRepository ordersRepository;
    private final UserRepo userRepo;
    private final HotelRepo hotelRepo;
    private final FoodRepo foodRepo;

    public FoodServiceImpl(HotelFoodRepo hotelFoodRepo, OrdersRepository ordersRepository, UserRepo userRepo, HotelRepo hotelRepo, FoodRepo foodRepo) {
        this.hotelFoodRepo = hotelFoodRepo;
        this.ordersRepository = ordersRepository;
        this.userRepo = userRepo;
        this.hotelRepo = hotelRepo;
        this.foodRepo = foodRepo;
    }

    @Override
    public SuccessResponse<Object> createFoodPrice(FoodDto foodDto) {
        SuccessResponse<Object> successResponse = new SuccessResponse<>();
        Optional<Hotel> hotelOptional = hotelRepo.findById(foodDto.getHotelId());
        if (hotelOptional.isPresent()) {
            Hotel hotel = hotelOptional.get();
            List<String> foodNames = foodDto.getFoods();
            List<String> prices = foodDto.getPrice();
            if (foodNames.size() == prices.size()) {
                List<HotelFood> hotelFoodList = new ArrayList<>();
                for (int i = 0; i < foodNames.size(); i++) {
                    Optional<Food> foodOptional = foodRepo.findById(foodNames.get(i));
                    if (foodOptional.isPresent()) {
                        Food food = foodOptional.get();
                        HotelFood hotelFood = new HotelFood();
                        hotelFood.setPrice(Long.valueOf(prices.get(i)));
                        hotelFood.setHotelId(hotel);
                        hotelFood.setFoodId(food);
                        hotelFoodList.add(hotelFood);
                    } else {
                        successResponse.setSuccess(false);
                        successResponse.setMessage("Invalid food ID: " + foodNames.get(i));
                        return successResponse;
                    }
                }
                hotelFoodRepo.saveAll(hotelFoodList);
                successResponse.setSuccess(true);
                successResponse.setMessage("Data saved successfully");
            }
        } else {
            successResponse.setSuccess(false);
            successResponse.setMessage("Invalid Hotel ID");
        }
        return successResponse;
    }

    @Override
    public SuccessResponse<Object> makeOrder(OrdersDto ordersDto) {
        SuccessResponse<Object> successResponse = new SuccessResponse<>();
        List<String> hotelFoodList = ordersDto.getHotelFoodId();
        List<String> quantity = ordersDto.getQuantity();
        if (hotelFoodList.size() == quantity.size()) {
            for (int i = 0; i < hotelFoodList.size(); i++) {
                Optional<HotelFood> hotelFoodOptional = hotelFoodRepo.findById((hotelFoodList.get(i)));
                if (hotelFoodOptional.isPresent()) {
                    HotelFood hotelFood = hotelFoodOptional.get();
                    Orders orders = new Orders();
                    orders.setQuantity(Long.valueOf(quantity.get(i)));
                    orders.setHotelFood(hotelFood);
                    Optional<User> userOptional = userRepo.findById((ordersDto.getUserId()));
                    if (userOptional.isPresent()) {
                        User user = userOptional.get();
                        orders.setUser(user);
                        ordersRepository.save(orders);
                        successResponse.setMessage("order created successfully");
                    } else {
                        successResponse.setMessage("invalid user details");
                    }
                } else {
                    successResponse.setMessage("please enter valid food id");
                }
            }
        } else {
            successResponse.setMessage("order not created due to invalid data");
        }
        return successResponse;
    }

    @Override
    public SuccessResponse<Object> getAllOrders() {
        SuccessResponse<Object> successResponse = new SuccessResponse<>();
        List<ViewDto> viewOrderList = new ArrayList<>();
        List<Orders> ordersOptional = ordersRepository.findAll();
        if (!ordersOptional.isEmpty()) {
            for (Orders allOrder : ordersOptional) {
                ViewDto viewDto = new ViewDto();
                viewDto.setUsername(allOrder.getUser().getName());
                viewDto.setHotelName(allOrder.getHotelFood().getHotelId().getHotelName());
                viewDto.setFoodName(allOrder.getHotelFood().getFoodId().getFoodName());
                viewDto.setQuantity(String.valueOf(allOrder.getQuantity()));
                viewDto.setDate(allOrder.getDate());
                viewDto.setPriceItems(String.valueOf(allOrder.getHotelFood().getPrice()));
                viewOrderList.add(viewDto);
                successResponse.setMessage("All order details are fetched successfully");
                successResponse.setData(viewOrderList);
            }
        } else {
            successResponse.setMessage("No list of orders");
        }
        return successResponse;
    }

    @Override
    public SuccessResponse<List<UserOrdersDto>> getOrdersByUser(String userId) {
        SuccessResponse<List<UserOrdersDto>> response = new SuccessResponse<>();
        Optional<User> userOptional = userRepo.findById(userId);
        if (!userOptional.isPresent()) {
            userOptional = userRepo.findByName(userId);
        }
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            List<Orders> ordersList = ordersRepository.findByUser(user);
            List<UserOrdersDto> userOrdersDto = ordersList.stream().map(order -> {
                UserOrdersDto dto = new UserOrdersDto();
                dto.setId(order.getId());
                dto.setUserName(user.getName());
                dto.setFoodName(order.getHotelFood().getFoodId().getFoodName());
                dto.setQuantity(order.getQuantity().toString());
                dto.setPrice(order.getHotelFood().getPrice().toString());
                return dto;
            }).toList();
            response.setSuccess(true);
            response.setMessage("Orders successfully");
            response.setData(userOrdersDto);
        } else {
            response.setSuccess(false);
            response.setMessage("User not found");
        }
        return response;
    }

    @Override
    public SuccessResponse<Object> deleteOrderById(String orderId) {
        SuccessResponse<Object> response = new SuccessResponse<>();
        Orders orderOptional = ordersRepository.findById(orderId).orElseThrow(()-> new RuntimeException("Order not found"));
        ordersRepository.delete(orderOptional);
        response.setSuccess(true);
        response.setMessage("Order deleted successfully");
        return response;
    }

    @Override
    public SuccessResponse<Object> createFoods(FoodCreateDto foodCreateDto) {
        SuccessResponse<Object> successResponse = new SuccessResponse<>();
        Food food = new Food();
        food.setFoodName(foodCreateDto.getFoodName());
        Food savedFood = foodRepo.save(food);
        successResponse.setMessage("Food created successfully");
        successResponse.setData(savedFood);
        successResponse.setSuccess(true);
        return successResponse;
    }


    @Override
    public SuccessResponse<Object> createHotel(HotelDto hotelDto) {
        SuccessResponse<Object> successResponse = new SuccessResponse<>();
        Hotel hotel = new Hotel();
        hotel.setHotelName(hotelDto.getHotelName());
        Hotel savedHotel = hotelRepo.save(hotel);
        successResponse.setMessage("Hotel created successfully");
        successResponse.setData(savedHotel);
        successResponse.setSuccess(true);
        return successResponse;
    }

    @Override
    public SuccessResponse<Object> createUser(UserDto userDto) {
        SuccessResponse<Object> successResponse = new SuccessResponse<>();
        User user = new User();
        user.setName(userDto.getUserName());
        user.setPhone(userDto.getPhone());
        User savedUser = userRepo.save(user);
        successResponse.setMessage("User created successfully");
        successResponse.setData(savedUser);
        successResponse.setSuccess(true);
        return successResponse;
    }

    @Override
    public SuccessResponse<List<Food>> getAllFoods() {
        SuccessResponse<List<Food>> successResponse = new SuccessResponse<>();
        List<Food> foods = foodRepo.findAll();
        successResponse.setMessage("Food fetched successfully");
        successResponse.setData(foods);
        successResponse.setSuccess(true);
        return successResponse;
    }

    @Override
    public SuccessResponse<Food> getFoodById(String id) {
        SuccessResponse<Food> successResponse = new SuccessResponse<>();
        Optional<Food> foodOptional = foodRepo.findById(id);
        if (foodOptional.isPresent()) {
            successResponse.setMessage("Food fetched successfully");
            successResponse.setData(foodOptional.get());
            successResponse.setSuccess(true);
        } else {
            successResponse.setMessage("Food not found");
            successResponse.setData(null);
            successResponse.setSuccess(false);
        }
        return successResponse;
    }

    @Override
    public SuccessResponse<List<UserOrdersDto>> filterOrders(String userName, String foodName, LocalDate date) {
        SuccessResponse<List<UserOrdersDto>> response = new SuccessResponse<>();
        List<Orders> ordersList;
        if (userName != null) {
            Optional<User> userOptional = userRepo.findByName(userName);
            if (userOptional.isEmpty()) {
                response.setSuccess(false);
                response.setMessage("User not found");
                return response;
            }
            User user = userOptional.get();
            ordersList = ordersRepository.findByUser(user);
        } else {
            ordersList = ordersRepository.findAll();
        }
        if (foodName != null) {
            ordersList = ordersList.stream()
                    .filter(order -> order.getHotelFood().getFoodId().getFoodName().equalsIgnoreCase(foodName)).toList();
        }
        if (date != null) {
            ordersList = ordersList.stream().filter(order -> order.getDate().equals(date)).toList();
        }
        List<UserOrdersDto> userOrdersDtos = ordersList.stream().map(order -> {
            UserOrdersDto dto = new UserOrdersDto();
            dto.setId(order.getId());
            dto.setUserName(order.getUser().getName());
            dto.setFoodName(order.getHotelFood().getFoodId().getFoodName());
            dto.setQuantity(order.getQuantity().toString());
            dto.setPrice(order.getHotelFood().getPrice().toString());
            return dto;
        }).toList();
        response.setSuccess(true);
        response.setMessage("Filtered orders retrieved successfully");
        response.setData(userOrdersDtos);
        return response;
    }

    @Override
    public SuccessResponse<List<AdminViewDto>> getOrdersByDate(LocalDate date) {
        List<Orders> ordersList = ordersRepository.findByDate(date);
        List<AdminViewDto> adminViewDtos = ordersList.stream().map(order -> {
            AdminViewDto dto = new AdminViewDto();
            dto.setDate(order.getDate());
            UserDto userDto = new UserDto();
            userDto.setId(order.getUser().getId());
            userDto.setUserName(order.getUser().getName());
            userDto.setPhone(order.getUser().getPhone());
            dto.setUser(userDto);
            HotelDto hotelDto = new HotelDto();
            hotelDto.setId(order.getHotelFood().getHotelId().getId());
            hotelDto.setHotelName(order.getHotelFood().getHotelId().getHotelName());
            dto.setHotel(hotelDto);
            FoodDto foodDto = new FoodDto();
            foodDto.setFoods(List.of(order.getHotelFood().getFoodId().getFoodName()));
            foodDto.setPrice(List.of(order.getHotelFood().getPrice().toString()));
            dto.setFood(List.of(foodDto));
            OrdersDto ordersDto = new OrdersDto();
            ordersDto.setHotelFoodId(List.of(order.getHotelFood().getId()));
            ordersDto.setQuantity(List.of(order.getQuantity().toString()));
            ordersDto.setUserId(order.getUser().getId());
            dto.setOrder(List.of(ordersDto));
            return dto;
        }).toList();
        SuccessResponse<List<AdminViewDto>> response = new SuccessResponse<>();
        response.setSuccess(true);
        response.setMessage("Orders retrieved successfully");
        response.setData(adminViewDtos);
        return response;
    }


    @Override
    public SuccessResponse<Object> getHotelFood(String id) {
        SuccessResponse<Object> response = new SuccessResponse<>();
        Optional<HotelFood> allFoods = hotelFoodRepo.findById(id);
        List<HotelFoodListDto> dtoList = allFoods.stream().map(hotelFood -> {

            HotelFoodListDto dto = new HotelFoodListDto();
            dto.setId(hotelFood.getId());
            dto.setHotelName(hotelFood.getHotelId().getHotelName());
            dto.setFoods(hotelFood.getFoodId().getFoodName());
            dto.setPrice(hotelFood.getPrice());
            return dto;
        }).toList();

        response.setMessage("Hotel food successfully");
        response.setData(dtoList);
        response.setSuccess(true);
        return response;
    }

}

