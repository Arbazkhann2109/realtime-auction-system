# Real-Time Online Auction System

A Spring Boot–based real-time auction platform that enables users to create auctions and place live bids using WebSocket (STOMP). The system uses JWT-based authentication and role-based access control.

---

## 🚀 Features (Current)
- User Registration & Login (JWT Authentication)
- Role-based Access (Buyer, Seller, Admin)
- Create and View Auctions
- Real-time Bidding using WebSocket (STOMP)
- MySQL Database Integration
- Spring Security with JWT Filter

---

## 🛠 Tech Stack
- Backend: Java 17, Spring Boot, Spring Security, JPA (Hibernate)
- Real-time: WebSocket (STOMP)
- Database: MySQL
- Tools: IntelliJ IDEA, Postman, GitHub

---

## ⚙️ Setup Instructions

1) Clone the repository  
bash
git clone https://github.com/Arbazkhann2109/realtime-auction-system.git

2) Create MySQL database
CREATE DATABASE auction_system;



3) Configure application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/auction_system
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update


4) Run the application
mvn spring-boot:run

🔌 API Endpoints
Auth
POST /api/auth/register

POST /api/auth/login

Auction
POST /api/auctions (JWT required)

GET /api/auctions

GET /api/auctions/live

WebSocket
Connect: ws://localhost:8080/ws

Subscribe: /topic/bids

Send Bid: /app/bid

🧪 Testing WebSocket (Postman)
Connect to: ws://localhost:8080/ws

Subscribe to: /topic/bids

Send:

{ "auctionId": 1, "amount": 52000 }
📌 Future Scope
Bid history & winner selection

Payment gateway integration

Email/Push notifications

Admin analytics dashboard

Frontend (React)

👨‍💻 Author
Arbaz Khan
GitHub: https://github.com/Arbazkhann2109

