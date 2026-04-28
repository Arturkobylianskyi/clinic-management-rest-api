# Clinic Management REST API

RESTful API for managing clinic patients, built with **Java** and **Spring Boot**. 

## Stack
* **Java 25+**
* **Spring Boot 4** (Web, Data JPA, Security)
* **Spring Security 6** (Role-Based Access Control, Custom UserDetailsService)
* **Hibernate / Spring Data JPA** 
* **MySQL** 
* **Swagger UI** (API Documentation)
* **Maven**
* **Postman** (Postman collection is include in `src/main/resources/sql`)

##  Security & Roles
The API uses Basic Authentication with BCrypt password hashing. Access is restricted based on custom roles stored in the database:
* **NURSE:** Can only view patient records (GET).
* **DOCTOR:** Can view, create, update, and modify patient records (GET, POST, PUT, PATCH).
* **ADMIN:** Has full access, including deleting records (DELETE) and also acces to swagger.ui.

## API Endpoints

| HTTP Method | Endpoint | Action | Required Role |
| :--- | :--- | :--- | :--- |
| `GET` | `/api/patients` | Get all patients | `NURSE`, `DOCTOR`, `ADMIN` |
| `GET` | `/api/patients/{id}` | Get patient by ID | `NURSE`, `DOCTOR`, `ADMIN` |
| `POST` | `/api/patients` | Add new patient | `DOCTOR`, `ADMIN` |
| `PUT` | `/api/patients` | Update patient | `DOCTOR`, `ADMIN` |
| `PATCH` | `/api/patients/{id}` | Partially update patient | `DOCTOR`, `ADMIN` |
| `DELETE` | `/api/patients/{id}`| Delete patient | `ADMIN` |

## Setup & Run
1. Clone the repository.
2. Execute the SQL scripts located in `src/main/resources/sql` to create the `clinic_db`, `user`, `role`, `patients`, and `users_roles` tables.
3. Update `src/main/resources/application.properties` with your MySQL credentials:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/clinic_db
   spring.datasource.username=your_username
   spring.datasource.password=your_password
