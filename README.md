# Journal Application

A RESTful backend service for managing personal journal entries. Developed using **Spring Boot** and **MongoDB**, following a clean layered architecture.

---

## Features

* Create, read, update, and delete journal entries
* MongoDB persistence
* Controller → Service → Repository layered structure
* Swagger UI for API documentation
* Easy to extend for authentication (JWT, OAuth, etc.)

---

## Technology Stack

| Component  | Technology             |
| ---------- | ---------------------- |
| Language   | Java (Spring Boot)     |
| Database   | MongoDB                |
| Build Tool | Maven                  |
| API Docs   | Springdoc / Swagger UI |

---

## Project Structure

```
src/main/java
 └─ com.example.journal
     ├─ controller
     ├─ service
     ├─ repository
     ├─ model
     └─ JournalApplication.java
```

---

## Prerequisites

* Java 17+
* MongoDB (Local or Cloud)
* Maven

---

## Setup Instructions

### Clone the Repository

```bash
git clone https://github.com/raosujay/journalApplication.git
cd journalApplication
```

### Configure MongoDB Connection

Edit `src/main/resources/application.yml`:

```yaml
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/journal_db
server:
  port: 8080
```

### Run the Application

```bash
./mvnw spring-boot:run
```

or

```bash
mvn clean package
java -jar target/*.jar
```

---

## API Endpoints

| Method | Endpoint                | Description                      |
| ------ | ----------------------- | -------------------------------- |
| POST   | `/api/v1/journals`      | Create a new journal entry       |
| GET    | `/api/v1/journals`      | Retrieve all journal entries     |
| GET    | `/api/v1/journals/{id}` | Retrieve a journal entry by ID   |
| PUT    | `/api/v1/journals/{id}` | Update an existing journal entry |
| PATCH  | `/api/v1/journals/{id}` | Partially update a journal entry |
| DELETE | `/api/v1/journals/{id}` | Delete a journal entry           |

---

## Example Request

```json
POST /api/v1/journals
{
  "title": "Morning Reflection",
  "content": "Went for a walk and planned the day.",
  "tags": ["personal"]
}
```

---

## Swagger API Documentation

Once the application is running, open:

```
http://localhost:8080/swagger-ui.html
```
