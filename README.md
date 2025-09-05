# journalApplication
A Journal Management Application built with Spring Boot and MongoDB. It provides RESTful APIs to create, read, update, and delete journal entries. The project demonstrates backend development with Java, dependency injection, and database integration using Spring Boot.


### 📓 Journal Application – Spring Boot

A simple **Journal Management Application** built using **Spring Boot** and **MongoDB**. This project demonstrates how to build a full-stack backend service for storing, retrieving, updating, and deleting journal entries.

#### 🔑 Features

* ✍️ Create new journal entries with title and content
* 📖 View all journal entries or fetch by ID
* ✏️ Update existing journal entries
* 🗑️ Delete journal entries
* 🗂️ MongoDB integration for data persistence
* ⚡ RESTful API endpoints using Spring Boot

#### 🛠️ Tech Stack

* **Java**
* **Spring Boot** (REST APIs, Dependency Injection)
* **MongoDB** (Database)
* **Maven** (Build & Dependency Management)

#### 🚀 Getting Started

1. Clone the repo:

   ```bash
   git clone https://github.com/your-username/journal-application.git
   ```
2. Navigate to the project folder and run:

   ```bash
   mvn spring-boot:run
   ```
3. Access the APIs at:

   ```
   http://localhost:8080/api/journals
   ```

#### 📌 Example Endpoints

* `GET /api/journals` → Fetch all journals
* `GET /api/journals/{id}` → Get journal by ID
* `POST /api/journals` → Add a new journal
* `PUT /api/journals/{id}` → Update a journal
* `DELETE /api/journals/{id}` → Delete a journal

---
