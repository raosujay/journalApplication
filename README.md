# Journal Application

A production-ready Spring Boot application that provides secure journal entry management with user authentication, Redis caching, email notifications, scheduled tasks, and integration with external APIs.

---

## Features

* User authentication and authorization using JWT
* Role-based access control (Admin and User)
* CRUD operations for journal entries
* Redis caching for frequently accessed data
* Email notification service
* Scheduled user activity checks
* Weather API integration for real-time data fetch
* Swagger UI for API testing and documentation
* MongoDB as the primary data store

---

## Technology Stack

| Component | Technology            |
| --------- | --------------------- |
| Language  | Java 17+              |
| Framework | Spring Boot           |
| Database  | MongoDB               |
| Caching   | Redis                 |
| Security  | Spring Security + JWT |
| Scheduler | Spring Task Scheduler |
| API Docs  | Swagger (springdoc)   |
| Build     | Maven                 |

---

## Project Structure

```
src/main/java/com/sujay/journalApplication
 ├── Cache
 ├── Config
 │    ├── RedisConfig.java
 │    ├── SpringSecurity.java
 │    ├── SpringSecurityProd.java
 │    └── SwaggerConfig.java
 ├── Controller
 │    ├── AdminController.java
 │    ├── JournalEntryController.java
 │    ├── PublicController.java
 │    └── UserController.java
 ├── DTO
 ├── Exception
 ├── Model
 ├── Repository
 ├── Scheduler
 │    └── UserScheduler.java
 ├── Service
 │    ├── EmailService.java
 │    ├── JournalEntryService.java
 │    ├── RedisService.java
 │    ├── UserService.java
 │    └── WeatherService.java
 ├── Utils
 │    └── JwtUtil.java
 └── JournalApplication.java
```

---

## Prerequisites

* Java 17 or higher
* MongoDB installed locally or cloud URI
* Redis installed locally or Redis Cloud instance
* Maven

---

## Configuration

### MongoDB

```
spring.data.mongodb.uri=mongodb://localhost:27017/journal_db
```

### Redis

```
spring.redis.host=localhost
spring.redis.port=6379
```

### JWT Secret

```
jwt.secret=<your-secret-key>
jwt.expiration=86400000
```

### Email SMTP (if used)

```
spring.mail.host=smtp.gmail.com
spring.mail.username=<email>
spring.mail.password=<app-password>
```

Set these in `application.yml` or environment variables.

---

## Running the Application

```bash
./mvnw spring-boot:run
```

or

```bash
mvn clean package
java -jar target/*.jar
```

Server runs at:

```
http://localhost:8080
```

---

## API Endpoints

### Authentication

| Method | Endpoint           | Description                    |
| ------ | ------------------ | ------------------------------ |
| POST   | `/public/register` | User registration              |
| POST   | `/public/login`    | User login (returns JWT token) |

### Journal Management

| Method | Endpoint               | Description                            |
| ------ | ---------------------- | -------------------------------------- |
| GET    | `/journal/all`         | Get journal entries for logged-in user |
| POST   | `/journal/add`         | Add a new entry                        |
| PUT    | `/journal/update/{id}` | Update entry                           |
| DELETE | `/journal/delete/{id}` | Delete entry                           |

### Admin

| Method | Endpoint           | Description               |
| ------ | ------------------ | ------------------------- |
| GET    | `/admin/all-users` | View all registered users |

Authorization Header Required:

```
Authorization: Bearer <token>
```

---

## Swagger Documentation

Open in browser:

```
http://localhost:8080/swagger-ui.html
```

---

## Scheduled Tasks

* `UserScheduler` runs periodic checks and automated actions.

---

## Weather API Integration

Weather data is fetched from an external API and returned in response format defined in `WeatherResponse.java`.

---

## Redis Caching

Frequently used user-related data is cached through `RedisService` to improve performance.

---

## Tests

JUnit and Spring Boot test cases are located in:

```
src/test/java/com/sujay/journalApplication
```
