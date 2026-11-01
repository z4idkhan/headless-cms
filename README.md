# Headless CMS

A backend-focused Headless Content Management System built using **Java, Spring Boot, Spring Security, and PostgreSQL**. It provides secure REST APIs for managing content independently of any frontend application.

The project follows the headless CMS architecture, separating content management and delivery from the presentation layer. This allows different clients, such as React applications, mobile applications, or other services, to consume content through APIs.

## Overview

Traditional CMS platforms often combine content management with frontend rendering. A Headless CMS separates these responsibilities.

This project provides a centralized backend for creating, managing, and publishing content through RESTful APIs, allowing any frontend application to consume the published content.

## Features

- **Authentication & Authorization:** Secure API access using Spring Security and JWT authentication.
- **Content Management:** Create, update, retrieve, and manage content.
- **Draft & Publish Workflow:** Manage content in draft state before making it publicly available.
- **RESTful APIs:** Expose content and management functionality through JSON-based APIs.
- **Frontend Independent:** Consume the same backend APIs from different frontend applications.
- **Database Persistence:** Store and manage content using PostgreSQL.
- **Secure Endpoints:** Protect management operations from unauthorized access.
- **React Dashboard:** A separate frontend application for interacting with the CMS.

## Tech Stack

| Technology | Purpose |
|---|---|
| Java | Backend programming language |
| Spring Boot 3 | Backend application framework |
| Spring Web | REST API development |
| Spring Security 6 | Authentication and authorization |
| JWT | Token-based authentication |
| Spring Data JPA | Database interaction |
| Hibernate | ORM |
| PostgreSQL | Relational database |
| Maven | Dependency management and build tool |
| React | Frontend dashboard |

## Architecture

The project follows a frontend-independent architecture.

```text
             Client Applications
                      |
          +-----------+-----------+
          |           |           |
        React       Mobile      Other
       Dashboard     App        Clients
          |           |           |
          +-----------+-----------+
                      |
                  REST APIs
                      |
              Spring Boot Backend
                      |
          +-----------+-----------+
          |           |           |
       Security     Services   Controllers
          |           |           |
          +-----------+-----------+
                      |
                Spring Data JPA
                      |
                 PostgreSQL
```

The backend handles authentication, content management, business logic, and database operations, while frontend applications are responsible for displaying the content.

## Content Management Workflow

The CMS follows a draft-to-publish workflow.

```text
       Create Content
             |
             v
       Draft Content
             |
             v
       Edit / Update
             |
             v
       Publish Content
             |
             v
     Available Through API
```

This workflow allows content managers to prepare and review content before making it available to consumers.

## Authentication Flow

The application uses JWT-based authentication with Spring Security.

1. A user submits login credentials.
2. The backend validates the credentials.
3. Upon successful authentication, a JWT is issued.
4. The client includes the token in subsequent API requests.
5. Spring Security validates the token before allowing access to protected resources.

## Frontend Dashboard

A separate React application demonstrates how the Headless CMS APIs can be consumed by a frontend client.

**Live Demo:**  
https://content-management-system-eight.vercel.app/login

The frontend is independent of the backend, demonstrating how the CMS can serve content to different applications without requiring changes to its core business logic.

## Getting Started

### Prerequisites

Make sure you have the following installed:

- Java 21
- Maven
- PostgreSQL
- Git

### 1. Clone the Repository

```bash
git clone <YOUR_BACKEND_REPOSITORY_URL>
```

```bash
cd <YOUR_PROJECT_DIRECTORY>
```

### 2. Configure the Database

Create a PostgreSQL database for the application.

```sql
CREATE DATABASE headless_cms;
```

Configure your database connection in `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/headless_cms
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
```

### 3. Configure Environment Variables

Configure the required environment variables for database credentials and JWT settings.

```env
DB_USERNAME=your_username
DB_PASSWORD=your_password
JWT_SECRET=your_secret_key
```

Use your application's actual configuration property names if they differ.

### 4. Run the Application

Using Maven:

```bash
mvn spring-boot:run
```

Alternatively:

```bash
mvn clean package
java -jar target/*.jar
```

The application will start on its configured Spring Boot port.

## Project Goals

This project demonstrates practical backend engineering concepts, including:

- REST API design and development.
- Authentication and authorization using Spring Security.
- JWT-based stateless authentication.
- Relational database integration.
- Backend business logic and content lifecycle management.
- Separation of backend and frontend responsibilities.
- Designing reusable APIs for multiple client applications.

## Future Improvements

- Role-based access control with granular permissions.
- Dynamic content types and custom fields.
- Media upload and management.
- Content versioning and revision history.
- Audit logging.
- API documentation using OpenAPI/Swagger.
- Pagination, filtering, and search.
- Automated integration and unit testing.
- Docker-based deployment.

## License

This project is licensed under the MIT License.
