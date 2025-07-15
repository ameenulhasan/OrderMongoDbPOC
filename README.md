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
POST /orders

json
Copy
Edit
{
  "customerName": "Ameen",
  "product": "Laptop",
  "quantity": 2,
  "price": 55000
}

📄 Get All Orders
GET /orders

🔍 Get Order by ID
GET /orders/{id}

📝 Update Order
PUT /orders/{id}

❌ Delete Order
DELETE /orders/{id}

▶️ Running the App
    1.Clone the repo
    2.Start MongoDB locally (or configure URI)
    3.Run the application:
        ./mvnw spring-boot:run
    4.Test API using Postman, Swagger UI, or any REST client

📫 Contact

Maintained by Ameenul Hasan – feel free to reach out for collaboration or feedback.

---
