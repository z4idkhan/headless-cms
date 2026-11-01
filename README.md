# Headless CMS

[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

A Headless Content Management System built with **Java, Spring Boot, Spring Security, and PostgreSQL**.

The idea behind this project is simple: manage content in one place and make it available to different applications through REST APIs, without tying the backend to a particular frontend.

## Overview

Unlike a traditional CMS, where content management and frontend rendering are handled together, a headless CMS keeps them separate.

This project provides APIs for creating, updating, managing, and publishing content. A React dashboard serves as one example of how a frontend application can interact with the backend.

## Features

- **Authentication:** User authentication using Spring Security and JWT.
- **Content Management:** Create, update, retrieve, and manage content.
- **Draft & Publish:** Keep content in draft until it is ready to be published.
- **REST APIs:** Manage and deliver content through JSON-based endpoints.
- **Frontend Independent:** Allow different applications to consume the same APIs.
- **PostgreSQL Integration:** Persist application data using Spring Data JPA and Hibernate.
- **React Dashboard:** A separate frontend for interacting with the CMS.

## Tech Stack

| Technology | Usage |
|---|---|
| Java 21 | Backend development |
| Spring Boot 3 | Application framework |
| Spring Web | REST API development |
| Spring Security 6 | Authentication and authorization |
| JWT | Token-based authentication |
| Spring Data JPA | Database operations |
| Hibernate | ORM |
| PostgreSQL | Database |
| Maven | Build and dependency management |
| React | Frontend dashboard |

## Architecture

The backend is independent of the frontend. Any client capable of making HTTP requests can interact with the APIs.

```text
              Client Applications
                       |
          +------------+------------+
          |            |            |
        React        Mobile       Other
       Dashboard      App        Clients
          |            |            |
          +------------+------------+
                       |
                   REST APIs
                       |
               Spring Boot Backend
                       |
          +------------+------------+
          |            |            |
       Security    Controllers   Services
          |            |            |
          +------------+------------+
                       |
                 Spring Data JPA
                       |
                   PostgreSQL
```

## Content Workflow

Content can be created and maintained as a draft before being published.

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

## Authentication

The application uses JWT with Spring Security to protect its APIs.

The authentication process works as follows:

1. The user submits their login credentials.
2. The backend validates the credentials.
3. A JWT is generated after successful authentication.
4. The client sends the token with subsequent requests.
5. Spring Security validates the token before granting access to protected endpoints.

## Frontend Dashboard

The CMS has a separate React frontend that communicates with the backend through REST APIs.

**Live Demo:** [Headless CMS Dashboard](https://content-management-system-eight.vercel.app/login)

The dashboard is one example of a client application using the CMS. The backend can also serve other applications without requiring frontend-specific changes.

## Getting Started

### Prerequisites

Make sure you have the following installed:

- Java 21
- PostgreSQL
- Git

Maven can be used separately, or you can use the Maven Wrapper included in the repository.

### 1. Clone the Repository

```bash
git clone https://github.com/z4idkhan/headless-cms.git

cd headless-cms
```

### 2. Create the Database

Open PostgreSQL and create a database:

```sql
CREATE DATABASE headless_cms;
```

### 3. Configure Database Credentials

Update your `application.properties` with your PostgreSQL configuration:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/headless_cms
spring.datasource.username=${DB_USERNAME}
spring.datasource.password=${DB_PASSWORD}

spring.jpa.hibernate.ddl-auto=update
```

### 4. Set Environment Variables

Configure the required environment variables for your application:

```env
DB_USERNAME=your_username
DB_PASSWORD=your_password
JWT_SECRET=your_secret_key
```

Make sure the variable names match the configuration used in your application. Do not commit your credentials or secret keys.

### 5. Run the Application

Using the Maven Wrapper:

**Linux / macOS**

```bash
./mvnw spring-boot:run
```

**Windows**

```bash
mvnw.cmd spring-boot:run
```

Alternatively, build the application and run the generated JAR:

```bash
./mvnw clean package
java -jar target/*.jar
```

The application will start on its configured port.

## Future Improvements

Some things I'd like to work on next:

- Role-based access control and permissions.
- Dynamic content types and custom fields.
- Media upload and management.
- Content versioning and revision history.
- Audit logging.
- API documentation with Swagger / OpenAPI.
- Pagination, filtering, and search.
- Unit and integration testing.
- Docker-based deployment.

## License

This project is licensed under the [MIT License](LICENSE).
