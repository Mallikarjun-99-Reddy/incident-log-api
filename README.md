# Incident Log API

## Project Overview

The **Incident Log API** is a backend service designed to manage and log incidents. It uses **Spring Boot** for the backend and **MySQL** for data storage. This project doesn't include a frontend, and it is intended to be consumed by other services or applications that interact with the API.

## Features

- **Incident Management**: Create, update, and retrieve incidents.
- **Severity Handling**: Incidents can be categorized based on severity.
- **Date & Time**: The reported time of incidents is stored in `LocalDateTime` format.
- **Database**: MySQL is used to persist incident data.

## Database Schema

The MySQL database is used to store incidents. Here's the table schema for the **incident_log**:

- `id` (Primary Key) - Unique identifier for each incident.
- `title` - The title of the incident.
- `description` - A detailed description of the incident.
- `severity` - Severity level of the incident.
- `reported_at` - The timestamp when the incident was reported.

## Technologies Used

- **Spring Boot** for the backend
- **MySQL** for the database
- **JPA** (Java Persistence API) for interacting with the database
- **Hibernate** for ORM (Object-Relational Mapping)

## Setup and Installation

### Prerequisites

Before setting up the project, make sure you have the following installed:

- **Java 8 or higher**
- **Spring Boot**
- **MySQL**

### Steps to Run the Project

1. Clone the repository:

    ```bash
    git clone https://github.com/Mallikarjun-99-Reddy/incident-log-api.git
    cd incident-log-api
    ```

2. Set up MySQL:

   - Create a database named `incident_log` in MySQL.
   - Configure your MySQL credentials in `application.properties` file.

3. Build the project using Maven:

    ```bash
    ./mvnw clean install
    ```

4. Run the project:

    ```bash
    ./mvnw spring-boot:run
    ```

5. The API will start running locally on `http://localhost:8080`.

## Endpoints

Here are the available endpoints in the API:

### Get All Incidents
```bash
1. GET /incidents
2. GET /incidents/{id}
3. POST /incidents
    {
      "title": "Sample Incident",
      "description": "This is a sample incident description",
      "severity": "Low"
    }
4. PUT /api/incidents/{id}
    {
      "title": "Updated Incident Title",
      "description": "Updated description",
      "severity": "Medium"
    }
5. DELETE /api/incidents/{id}


