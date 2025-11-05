Got it — here is a clean, professional **README.md** you can copy-paste directly into your GitHub repo.
It is concise, developer-friendly, and matches a typical Spring Boot REST API project.

---

```markdown
# 📓 Journal Application (Spring Boot + MongoDB)

A simple and clean REST API to create and manage personal journal entries.  
Built with **Spring Boot**, **MongoDB**, and structured using service-repository architecture.

---

## 🚀 Features

- Create, read, update, and delete journal entries
- MongoDB persistence
- Layered architecture (Controller → Service → Repository)
- Ready for JWT-based authentication (can be enabled easily)
- Swagger UI available for exploring APIs

---

## 🧱 Tech Stack

| Layer        | Technology         |
|-------------|-------------------|
| Backend     | Spring Boot (Java) |
| Database    | MongoDB            |
| Build Tool  | Maven              |
| API Docs    | Springdoc / Swagger UI |

---

## 📁 Project Structure

```

src/main/java
└─ com.example.journal
├─ controller
├─ service
├─ repository
├─ model
└─ JournalApplication.java

````

---

## ⚙️ Setup & Run

### 1. Clone the Repository
```bash
git clone https://github.com/raosujay/journalApplication.git
cd journalApplication
````

### 2. Configure MongoDB

Update **`src/main/resources/application.yml`**:

```yaml
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/journal_db
server:
  port: 8080
```

### 3. Run the Application

```bash
./mvnw spring-boot:run
```

or

```bash
mvn clean package
java -jar target/*.jar
```

---

## 🧪 API Reference

| Method | Endpoint                | Description            |
| ------ | ----------------------- | ---------------------- |
| POST   | `/api/v1/journals`      | Create a journal entry |
| GET    | `/api/v1/journals`      | Get all entries        |
| GET    | `/api/v1/journals/{id}` | Get entry by ID        |
| PUT    | `/api/v1/journals/{id}` | Update full entry      |
| PATCH  | `/api/v1/journals/{id}` | Update partial fields  |
| DELETE | `/api/v1/journals/{id}` | Delete entry           |

---

## 🧑‍💻 Example Create Request

```json
POST /api/v1/journals
{
  "title": "A good day",
  "content": "Went for a walk and read a book.",
  "tags": ["personal","note"]
}
```

---

## 📖 Swagger UI

Once the app is running, open:

```
http://localhost:8080/swagger-ui.html
```

---

## 🔒 Optional: Enabling JWT Authentication

* Add JWT filter in `config/security`
* Add login controller returning token
* Require `Authorization: Bearer <token>` for journal endpoints

*(JWT code can be added if needed — just ask!)*

---

## 📝 License

This project is open-source and available for modification and use.

---

## 🤝 Contributing

Contributions, issues, and feature requests are welcome!

---
