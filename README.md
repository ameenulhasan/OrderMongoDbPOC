# 🛒 Order System (MongoDB + Spring Boot)

This is a Proof of Concept (POC) project demonstrating a simple **Order** system built using **Spring Boot** and **MongoDB**.

---

## 🚀 Features

- Create, Read, Update, Delete (CRUD) operations for orders
- MongoDB NoSQL database integration
- RESTful API endpoints
- JSON-based request/response
- Spring Data MongoDB repositories

---

## 🔧 Technologies Used

- Java 17+
- Spring Boot
- Spring Web
- Spring Data MongoDB
- Lombok
- MongoDB (local or Atlas)

---

## 📁 Folder Structure

OrderMongoDbPOC/
├── src/
│ ├── main/
│ │ ├── java/
│ │ │ └── com.ameen.order/
│ │ │ ├── controller/
│ │ │ ├── dto/
│ │ │ ├── model/
│ │ │ ├── repository/
│ │ │ ├── response/
│ │ │ ├── service/
│ │ │ └── serviceImpl
│ │ └── resources/
│ │ └── application.properties
│ └── test/
├── pom.xml
└── README.md

---

## 🛠️ MongoDB Configuration

Make sure MongoDB is running locally or provide URI:

```properties
spring.data.mongodb.uri=mongodb://localhost:27017/order-db

📦 Sample API Endpoints

➕ Create Order
POST /createFoodPrice
{
    "hotelId": "67d2a56a41007c5a2f50d935",
    "foods": [
        "67d013d10915b21bd490635f",
        "67d0162b0915b21bd4906360"
    ],
    "price": [
        "320",
        "270"
    ]
}

➕ Make Order
POST /makeOrder
{
    "hotelFoodId": [
        "6836e71d43479f63782d5033",
        "6836e71d43479f63782d5034"
    ],
    "quantity": [ 
        2,1
    ],
    "userId": "67cfe02452ece904cfb71236"
}

🔍 Get All Orders
GET /getAllOrders

🔍 Get Order by User
GET /getByOrderUser

📝 Create Food
POST /createFood
{
   "id": null,
   "foodName":"Briyani"
}

❌ Delete Order
DELETE /deleteOrderId

▶️ Running the App
    1.Clone the repo
    2.Start MongoDB locally (or configure URI)
    3.Run the application:
        ./mvnw spring-boot:run
    4.Test API using Postman

📫 Contact
Maintained by Ameenul Hasan – feel free to reach out for collaboration or feedback.

---
