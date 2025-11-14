# 📔 Journal Application

A secure, full-featured Spring Boot REST API for managing personal journal entries with authentication, caching, email notifications, and weather integration.

![Java](https://img.shields.io/badge/Java-17+-orange) ![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.7.16-brightgreen) ![MongoDB](https://img.shields.io/badge/MongoDB-Database-green) ![Redis](https://img.shields.io/badge/Redis-Cache-red)

---

## 📋 Table of Contents

- [Features](#-features)
- [Technology Stack](#-technology-stack)
- [Architecture](#-architecture)
- [Prerequisites](#-prerequisites)
- [Installation & Setup](#-installation--setup)
- [Configuration](#%EF%B8%8F-configuration)
- [Running the Application](#-running-the-application)
- [API Documentation](#-api-documentation)
- [API Endpoints](#-api-endpoints)
- [Authentication Flow](#-authentication-flow)
- [Testing](#-testing)
- [Project Structure](#-project-structure)
- [Key Features Explained](#-key-features-explained)
- [Troubleshooting](#-troubleshooting)
- [Contributing](#-contributing)

---

## ✨ Features

### Core Functionality
- ✅ **User Management**: Secure registration, login, and profile updates
- ✅ **Journal Entries**: Full CRUD operations (Create, Read, Update, Delete)
- ✅ **JWT Authentication**: Stateless token-based security
- ✅ **Role-Based Access Control**: User and Admin roles

### Advanced Features
- 🔐 **Spring Security**: Password encryption with BCrypt
- ⚡ **Redis Caching**: Fast data retrieval for weather and user data
- 📧 **Email Notifications**: Automated sentiment analysis reports
- 📅 **Scheduled Tasks**: Weekly sentiment analysis emails
- 🌤️ **Weather Integration**: Real-time weather data from external API
- 😊 **Sentiment Tracking**: Track mood in journal entries (Happy, Sad, Angry, Anxious)
- 📖 **Swagger UI**: Interactive API documentation and testing
- 🔄 **Transaction Management**: MongoDB transactions for data consistency
- 📝 **Logging**: Comprehensive logging with Logback

---

## 🛠 Technology Stack

| Component           | Technology               | Purpose                          |
|---------------------|--------------------------|----------------------------------|
| **Language**        | Java 8+                  | Backend programming              |
| **Framework**       | Spring Boot 2.7.16       | Application framework            |
| **Database**        | MongoDB                  | NoSQL data storage               |
| **Caching**         | Redis                    | High-speed data caching          |
| **Security**        | Spring Security + JWT    | Authentication & authorization   |
| **API Docs**        | Swagger (springdoc)      | Interactive API documentation    |
| **Email**           | Spring Mail (SMTP)       | Email notification service       |
| **Build Tool**      | Maven                    | Dependency & build management    |
| **Testing**         | JUnit 5                  | Unit & integration tests         |
| **Logging**         | Logback                  | Application logging              |

---

## 🏗 Architecture

```
┌─────────────┐
│   Client    │
└──────┬──────┘
       │ HTTP/REST
       ▼
┌─────────────────────────┐
│   Spring Boot API       │
│  ┌──────────────────┐   │
│  │  Controllers     │   │
│  └────────┬─────────┘   │
│           │             │
│  ┌────────▼─────────┐   │
│  │    Services      │   │
│  └────────┬─────────┘   │
│           │             │
│  ┌────────▼─────────┐   │
│  │  Repositories    │   │
│  └────────┬─────────┘   │
└───────────┼─────────────┘
            │
     ┌──────┴──────┐
     ▼             ▼
┌─────────┐   ┌────────┐
│ MongoDB │   │ Redis  │
└─────────┘   └────────┘
```

**Layered Architecture:**
- **Controller Layer**: Handles HTTP requests and responses
- **Service Layer**: Business logic and transaction management
- **Repository Layer**: Data access using Spring Data MongoDB
- **Security Layer**: JWT filters and authentication

---

## 📦 Prerequisites

Before you begin, ensure you have the following installed:

- **Java JDK 17+** ([Download](https://www.oracle.com/java/technologies/downloads/))
- **Maven 3.6+** ([Download](https://maven.apache.org/download.cgi))
- **MongoDB 4.4+** ([Download](https://www.mongodb.com/try/download/community))
- **Redis 6.0+** ([Download](https://redis.io/download))
- **Git** ([Download](https://git-scm.com/downloads))

### Verify Installations

```bash
java -version
mvn -version
mongod --version
redis-server --version
```

---

## 🚀 Installation & Setup

### 1. Clone the Repository

```bash
git clone https://github.com/raosujay/journal-application.git
cd journal-application
```

### 2. Install MongoDB

**Windows:**
```bash
# Download and install from MongoDB website
# Start MongoDB service
net start MongoDB
```

**macOS:**
```bash
brew tap mongodb/brew
brew install mongodb-community
brew services start mongodb-community
```

**Linux:**
```bash
sudo apt-get install -y mongodb
sudo systemctl start mongod
sudo systemctl enable mongod
```

### 3. Install Redis

**Windows:**
```bash
# Download Redis from GitHub releases
# Or use WSL
```

**macOS:**
```bash
brew install redis
brew services start redis
```

**Linux:**
```bash
sudo apt-get install redis-server
sudo systemctl start redis
sudo systemctl enable redis
```

### 4. Verify Services

```bash
# Check MongoDB
mongo --eval "db.version()"

# Check Redis
redis-cli ping
# Should return: PONG
```

---

## ⚙️ Configuration

### 1. Create `application.yml`

Create a file at `src/main/resources/application.yml`:

```yaml
spring:
  profiles:
    active: dev
  
  # MongoDB Configuration
  data:
    mongodb:
      uri: mongodb://localhost:27017/journal_db
  
  # Redis Configuration
  redis:
    host: localhost
    port: 6379
  
  # Email Configuration (Gmail example)
  mail:
    host: smtp.gmail.com
    port: 587
    username: your-email@gmail.com
    password: your-app-password
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true

# JWT Configuration
jwt_secret_key: your-super-secret-jwt-key-min-256-bits-long-for-security

# Weather API Configuration
weather_api_key: your-weather-api-key
```

### 2. Gmail App Password Setup

For email functionality:

1. Go to [Google Account Security](https://myaccount.google.com/security)
2. Enable 2-Factor Authentication
3. Generate an "App Password"
4. Use this password in `application.yml`

### 3. Weather API Setup

1. Sign up at [WeatherStack](https://weatherstack.com/) or similar service
2. Get your API key
3. Add to `application.yml`

### 4. MongoDB Initial Setup

The application will automatically create collections. Optionally, create a config document:

```javascript
// Connect to MongoDB
use journal_db

// Insert weather API configuration
db.config_journal_app.insertOne({
  key: "weather_api",
  value: "http://api.weatherstack.com/current?access_key=<apiKey>&query=<city>"
})
```

---

## 🏃 Running the Application

### Option 1: Using Maven Wrapper (Recommended)

```bash
# Linux/macOS
./mvnw spring-boot:run

# Windows
mvnw.cmd spring-boot:run
```

### Option 2: Using Maven

```bash
mvn clean install
mvn spring-boot:run
```

### Option 3: Build JAR and Run

```bash
mvn clean package
java -jar target/journal-application-0.0.1-SNAPSHOT.jar
```

### With Profile Selection

```bash
# Development profile
./mvnw spring-boot:run -Dspring-boot.run.profiles=dev

# Production profile
./mvnw spring-boot:run -Dspring-boot.run.profiles=prod
```

### Application Startup

Once started, you'll see:

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::               (v2.7.16)

Application started on port 8080
```

---

## 📚 API Documentation

### Swagger UI

Access interactive API documentation:

```
http://localhost:8080/swagger-ui.html
```

### API Base URL

```
http://localhost:8080
```

---

## 🔌 API Endpoints

### 🌐 Public Endpoints (No Authentication Required)

| Method | Endpoint              | Description                  | Request Body                          |
|--------|-----------------------|------------------------------|---------------------------------------|
| GET    | `/public/health-check`| Check if API is running      | None                                  |
| POST   | `/public/signup`      | Register new user            | `{ "userName", "password", "email" }` |
| POST   | `/public/login`       | Login and get JWT token      | `{ "userName", "password" }`          |

### 📝 Journal Endpoints (Authentication Required)

| Method | Endpoint                  | Description                     | Request Body                                     |
|--------|---------------------------|---------------------------------|--------------------------------------------------|
| POST   | `/journal/post`           | Create new journal entry        | `{ "title", "content", "sentiment" }`            |
| GET    | `/journal/get-all`        | Get all user's journal entries  | None                                             |
| GET    | `/journal/get-byId/{id}`  | Get specific journal entry      | None                                             |
| PUT    | `/journal/update/{id}`    | Update journal entry            | `{ "title", "content" }`                         |
| DELETE | `/journal/delete/{id}`    | Delete journal entry            | None                                             |

### 👤 User Endpoints (Authentication Required)

| Method | Endpoint             | Description                    | Request Body                          |
|--------|----------------------|--------------------------------|---------------------------------------|
| PUT    | `/user/update`       | Update user details            | `{ "userName", "password", "email" }` |
| DELETE | `/user/delete-user`  | Delete user account            | None                                  |
| GET    | `/user/weather`      | Get greeting with weather info | None                                  |

### 👑 Admin Endpoints (Admin Role Required)

| Method | Endpoint                       | Description                | Request Body                          |
|--------|--------------------------------|----------------------------|---------------------------------------|
| GET    | `/admin/all-users`             | Get all registered users   | None                                  |
| POST   | `/admin/create-admin-user`     | Create new admin user      | `{ "userName", "password", "email" }` |

---

## 🔐 Authentication Flow

### 1. Sign Up

```bash
curl -X POST http://localhost:8080/public/signup \
  -H "Content-Type: application/json" \
  -d '{
    "userName": "john_doe",
    "password": "SecurePass123",
    "email": "john@example.com",
    "sentimentAnalysis": true
  }'
```

**Response:**
```
User created successfully
```

### 2. Login

```bash
curl -X POST http://localhost:8080/public/login \
  -H "Content-Type: application/json" \
  -d '{
    "userName": "john_doe",
    "password": "SecurePass123"
  }'
```

**Response:**
```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJqb2huX2RvZSIsImlhdCI6MTYxNjIzOTAyMiwiZXhwIjoxNjE2MjQyNjIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
```

### 3. Use JWT Token

For all protected endpoints, include the token in the `Authorization` header:

```bash
curl -X GET http://localhost:8080/journal/get-all \
  -H "Authorization: Bearer YOUR_JWT_TOKEN_HERE"
```

### 4. Create Journal Entry

```bash
curl -X POST http://localhost:8080/journal/post \
  -H "Authorization: Bearer YOUR_JWT_TOKEN" \
  -H "Content-Type: application/json" \
  -d '{
    "title": "My First Entry",
    "content": "Today was a great day!",
    "sentiment": "Happy"
  }'
```

---

## 🧪 Testing

### Run All Tests

```bash
mvn test
```

### Run Specific Test Class

```bash
mvn test -Dtest=UserServiceTests
```

### Test Coverage

The project includes tests for:
- ✅ User services
- ✅ Repository implementations
- ✅ Email services
- ✅ Redis caching
- ✅ Scheduled tasks

---

## 📁 Project Structure

```
journal-application/
│
├── src/main/java/com/sujay/journalApplication/
│   ├── Cache/
│   │   └── AppCache.java                    # Application-level cache
│   │
│   ├── Config/
│   │   ├── RedisConfig.java                 # Redis configuration
│   │   ├── SpringSecurity.java              # Security config (dev)
│   │   ├── SpringSecurityProd.java          # Security config (prod)
│   │   └── SwaggerConfig.java               # API documentation config
│   │
│   ├── Controller/
│   │   ├── AdminController.java             # Admin operations
│   │   ├── JournalEntryController.java      # Journal CRUD operations
│   │   ├── PublicController.java            # Public endpoints
│   │   └── UserController.java              # User operations
│   │
│   ├── DTO/
│   │   ├── AdminUserDTO.java                # Admin user data transfer
│   │   ├── JournalEntryCreateDTO.java       # Journal creation data
│   │   ├── JournalEntryResponseDTO.java     # Journal response data
│   │   ├── LoginDTO.java                    # Login request data
│   │   ├── UpdateUserDTO.java               # User update data
│   │   └── UserDTO.java                     # User registration data
│   │
│   ├── Entity/
│   │   ├── ConfigJournalAppEntity.java      # App configuration
│   │   ├── JournalEntry.java                # Journal entry model
│   │   └── User.java                        # User model
│   │
│   ├── Enums/
│   │   └── Sentiment.java                   # Sentiment types
│   │
│   ├── Filter/
│   │   └── JwtFilter.java                   # JWT authentication filter
│   │
│   ├── Repository/
│   │   ├── ConfigJournalAppRepository.java  # Config data access
│   │   ├── JournalEntryRepository.java      # Journal data access
│   │   ├── UserRepository.java              # User data access
│   │   └── UserRepositoryImpl.java          # Custom user queries
│   │
│   ├── Scheduler/
│   │   └── UserScheduler.java               # Scheduled tasks
│   │
│   ├── Service/
│   │   ├── EmailService.java                # Email functionality
│   │   ├── JournalEntryService.java         # Journal business logic
│   │   ├── RedisService.java                # Caching service
│   │   ├── UserDetailsServiceImpl.java      # Spring Security user details
│   │   ├── UserService.java                 # User business logic
│   │   └── WeatherService.java              # Weather API integration
│   │
│   ├── Utils/
│   │   └── JwtUtil.java                     # JWT token utilities
│   │
│   ├── api/response/
│   │   └── WeatherResponse.java             # Weather API response
│   │
│   └── JournalApplication.java              # Main application class
│
├── src/main/resources/
│   ├── application.yml                      # Application configuration
│   └── logback.xml                          # Logging configuration
│
├── src/test/                                # Test files
│
├── .gitignore                               # Git ignore rules
├── pom.xml                                  # Maven dependencies
└── README.md                                # This file
```

---

## 🎯 Key Features Explained

### 1. JWT Authentication

- **Token Generation**: On successful login, a JWT token is generated with 1-hour expiration
- **Token Validation**: `JwtFilter` intercepts requests and validates tokens
- **Stateless**: No server-side session storage required

### 2. Redis Caching

- **Weather Data**: Cached for 5 minutes to reduce API calls
- **Performance**: Significantly improves response times
- **TTL (Time To Live)**: Automatic cache expiration

### 3. Sentiment Analysis

- **Tracking**: Users can tag entries with emotions (Happy, Sad, Angry, Anxious)
- **Weekly Reports**: Automated email with most frequent sentiment
- **Scheduled Task**: Runs every Sunday at 9 AM

### 4. Role-Based Access

- **USER Role**: Can manage own journal entries
- **ADMIN Role**: Can view all users and create admin accounts
- **Authorization**: Checked using Spring Security

### 5. Transaction Management

- **MongoDB Transactions**: Ensures data consistency
- **Rollback Support**: Failed operations are automatically rolled back

---

## 🐛 Troubleshooting

### Common Issues

#### 1. MongoDB Connection Failed

**Error:** `MongoTimeoutException: Timed out after 30000 ms`

**Solution:**
```bash
# Check if MongoDB is running
sudo systemctl status mongod

# Start MongoDB
sudo systemctl start mongod
```

#### 2. Redis Connection Failed

**Error:** `Cannot get Jedis connection`

**Solution:**
```bash
# Check if Redis is running
redis-cli ping

# Start Redis
sudo systemctl start redis
```

#### 3. JWT Token Expired

**Error:** `401 Unauthorized`

**Solution:**
- Login again to get a new token
- Tokens expire after 1 hour

#### 4. Port Already in Use

**Error:** `Port 8080 is already in use`

**Solution:**
```bash
# Find process using port 8080
# Linux/macOS
lsof -i :8080

# Windows
netstat -ano | findstr :8080

# Kill the process or change port in application.yml
```

#### 5. Email Not Sending

**Solution:**
- Verify Gmail App Password is correct
- Ensure 2FA is enabled on Google Account
- Check firewall settings for SMTP port 587

---

## 📊 Database Schema

### Users Collection

```javascript
{
  "_id": ObjectId("..."),
  "userName": "john_doe",
  "password": "$2a$10$...",  // BCrypt hashed
  "email": "john@example.com",
  "sentimentAnalysis": true,
  "sentiment": "Happy",
  "roles": ["USER"],
  "journalEntries": [
    DBRef("journal_entries", ObjectId("..."))
  ]
}
```

### Journal Entries Collection

```javascript
{
  "_id": ObjectId("..."),
  "title": "My Day",
  "content": "Today was wonderful!",
  "date": ISODate("2024-11-14T10:30:00Z"),
  "sentiment": "Happy"
}
```

### Config Collection

```javascript
{
  "_id": ObjectId("..."),
  "key": "weather_api",
  "value": "http://api.weatherstack.com/current?access_key=<apiKey>&query=<city>"
}
```

---

## 🔒 Security Best Practices

1. **Never commit secrets**: Use environment variables or external config
2. **Change default JWT secret**: Use a strong, random 256-bit key
3. **Use HTTPS in production**: Enable SSL/TLS
4. **Update dependencies**: Regularly check for security updates
5. **Input validation**: Always validate user inputs
6. **Rate limiting**: Implement API rate limiting in production

---

## 🚢 Deployment

### Docker Deployment (Future Enhancement)

```dockerfile
FROM openjdk:17-jdk-slim
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

### Environment Variables for Production

```bash
export SPRING_PROFILES_ACTIVE=prod
export MONGODB_URI=mongodb://your-prod-mongodb-uri
export REDIS_HOST=your-redis-host
export JWT_SECRET=your-production-secret
export WEATHER_API_KEY=your-api-key
```

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

---

## 👤 Author

**Sujay S**

- GitHub: [@sujay](https://github.com/sujay)
- Email: sujay.92@yahoo.com

---

## 🙏 Acknowledgments

- Spring Boot Team
- MongoDB Team
- Redis Team
- All open-source contributors

---

## 📞 Support

For issues and questions:

1. Check [Troubleshooting](#-troubleshooting) section
2. Review [Swagger Documentation](http://localhost:8080/swagger-ui.html)
3. Open an issue on GitHub
4. Contact the author

---

**Happy Journaling! 📔✨**
