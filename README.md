# shorten_url

<p align="center">
  <img src="docs/images/home.png" width="600">
</p>

A URL shortening application built with **Spring Boot** and **PostgreSQL**, featuring automatic URL expiration.

---

## Prerequisites

Before running the application, make sure you have the following installed:

- Docker  
- Docker Compose  
- Java 17+ (or the Java version used in this project)

---

## Database (PostgreSQL)

The application uses PostgreSQL running in a Docker container.

### Start the database

From the project root directory, run:

```bash
docker compose up -d
```

This will:
- Start a PostgreSQL container
- Initialize the database required by the application

**Important:**  
If possible, change the database credentials (username, password, and database name) in the `docker-compose.yml` file, especially for production environments.

---

## Application Configuration

All main configurations are located in:

```
application.properties
```

---

## `http.body` property

The application uses a property called:

```properties
http.body
```

This property defines the **base domain** used to generate shortened URLs.

### Default configuration (local environment)

```properties
server.port=8080
http.body=http://localhost:${server.port}/
```

By default, the application assumes it is running locally on port **8080**.

---

## Production setup

When deploying the application to production, you **must update** the `http.body` property to match the public domain where the application will run.

Example:

```properties
http.body=https://yourdomain.com/
```

This ensures that generated short URLs correctly point to the production domain.

---

## Database configuration

You can configure the database connection in `application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/url_shortener
spring.datasource.username=admin
spring.datasource.password=admin123
```

 For production, **never use default credentials**.

---

## URL expiration

- Each shortened URL has a limited lifetime
- After expiration, the URL automatically stops working


---

## Final notes

- Ensure Docker is running before starting the application
- Adjust configuration values according to the environment (local vs production)
- Avoid committing sensitive credentials to public repositories