# Veterinary Management System

This project is an API developed to manage the business processes of a veterinary clinic. The system will be used by veterinary staff and includes doctor, customer, and animal management, vaccine records, and appointment tracking.

## Contents

- [Technologies Used](#technologies-used)
- [Project Architecture](#project-architecture)
- [Setup](#setup)
- [API Usage](#api-usage)
- [Database Schema](#database-schema)


## Technologies Used
<code><img width="50" src="https://user-images.githubusercontent.com/25181517/117201156-9a724800-adec-11eb-9a9d-3cd0f67da4bc.png" alt="Java" title="Java"/></code>
<code><img width="50" src="https://user-images.githubusercontent.com/25181517/183891303-41f257f8-6b3d-487c-aa56-c497b880d0fb.png" alt="Spring Boot" title="Spring Boot"/></code>
<code><img width="50" src="https://user-images.githubusercontent.com/25181517/117207242-07d5a700-adf4-11eb-975e-be04e62b984b.png" alt="Maven" title="Maven"/></code>
<code><img width="50" src="https://user-images.githubusercontent.com/25181517/183896128-ec99105a-ec1a-4d85-b08b-1aa1620b2046.png" alt="PostgreSQL" title="PostgreSQL"/></code>
<code><img width="50" src="https://user-images.githubusercontent.com/25181517/192107858-fe19f043-c502-4009-8c47-476fc89718ad.png" alt="REST" title="REST"/></code>
<code><img width="50" src="https://user-images.githubusercontent.com/25181517/186711335-a3729606-5a78-4496-9a36-06efcc74f800.png" alt="Swagger" title="Swagger"/></code>
<code><img width="50" src="https://user-images.githubusercontent.com/25181517/192109061-e138ca71-337c-4019-8d42-4792fdaa7128.png" alt="Postman" title="Postman"/></code>
<code><img width="50" src="https://user-images.githubusercontent.com/25181517/192108890-200809d1-439c-4e23-90d3-b090cf9a4eea.png" alt="IntelliJ IDEA" title="IntelliJ IDEA"/></code>
<code><img width="50" src="https://user-images.githubusercontent.com/25181517/192108372-f71d70ac-7ae6-4c0d-8395-51d8870c2ef0.png" alt="Git" title="Git"/></code>
<code><img width="50" src="https://user-images.githubusercontent.com/25181517/192108374-8da61ba1-99ec-41d7-80b8-fb2f7c0a4948.png" alt="GitHub" title="GitHub"/></code>


## Project Architecture


The project follows a layered architecture. The main package structure is as follows:

- `controller`: API endpoints
- `business`: Business logic
- `repository`: Database operations
- `dto`: Data Transfer Objects (Request and Response)
- `entities`: Database entities
- `core`: Utility classes and configurations

### UML Diagram

![VeterinaryProject_UML](https://github.com/Yldrmceyy/VeterinaryProject/assets/106755050/ba7ade62-00e9-46cb-9953-fa18dd1636e0)


## Setup

1. Clone the project:

    ```sh
    git clone https://github.com/Yldrmceyy/VeterinaryProject.git
    cd VeterinaryProject
    ```

2. Install the necessary dependencies:

    ```sh
    mvn install
    ```

3. Configure the `application.properties` file with your database connection details:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/Vet_Cey
    spring.datasource.username=postgres
    spring.datasource.password=postgres
    spring.jpa.hibernate.ddl-auto=update
    ```

4. Run the application:

    ```sh
    mvn spring-boot:run
    ```

5. Load the sample data into the database (use the .sql file provided in the project folder).

## API Usage

### Customer Management

- **Add Customer**
    - Endpoint: `POST /api/customers`
    - Request Body:
      ```json
      {
          "name": "John Doe",
          "phone": "1234567890",
          "mail": "john@example.com",
          "address": "123 Main St",
          "city": "Anytown"
      }
      ```

- **Update Customer Information**
    - Endpoint: `PUT /api/customers/{id}`
    - Request Body: Same as above

- **View Customer Information**
    - Endpoint: `GET /api/customers/{id}`

- **Delete Customer**
    - Endpoint: `DELETE /api/customers/{id}`

### Animal Management

- **Add Animal**
    - Endpoint: `POST /api/animals`
    - Request Body:
      ```json
      {
          "name": "Buddy",
          "species": "Dog",
          "breed": "Labrador",
          "gender": "Male",
          "colour": "Yellow",
          "dateOfBirth": "2018-06-01",
          "customerId": 1
      }
      ```

- **Update Animal Information**
    - Endpoint: `PUT /api/animals/{id}`
    - Request Body: Same as above

- **View Animal Information**
    - Endpoint: `GET /api/animals/{id}`

- **Delete Animal**
    - Endpoint: `DELETE /api/animals/{id}`

### Vaccine Management

- **Add Vaccine**
    - Endpoint: `POST /api/vaccines`
    - Request Body:
      ```json
      {
          "name": "Rabies",
          "code": "RAB123",
          "protectionStartDate": "2023-01-01",
          "protectionFinishDate": "2024-01-01",
          "animalId": 1
      }
      ```

- **Update Vaccine Information**
    - Endpoint: `PUT /api/vaccines/{id}`
    - Request Body: Same as above

- **View Vaccine Information**
    - Endpoint: `GET /api/vaccines/{id}`

- **Delete Vaccine**
    - Endpoint: `DELETE /api/vaccines/{id}`

### Appointment Management

- **Add Appointment**
    - Endpoint: `POST /api/appointments`
    - Request Body:
      ```json
      {
          "appointmentDate": "2023-06-15T14:00:00",
          "doctorId": 1,
          "animalId": 1
      }
      ```

- **Update Appointment Information**
    - Endpoint: `PUT /api/appointments/{id}`
    - Request Body: Same as above

- **View Appointment Information**
    - Endpoint: `GET /api/appointments/{id}`

- **Delete Appointment**
    - Endpoint: `DELETE /api/appointments/{id}`

## Database Schema

The database schema includes the following tables:

- `customers`: Customer information
- `animals`: Animal information
- `vaccines`: Vaccine information
- `doctors`: Doctor information
- `available_dates`: Doctor availability dates
- `appointments`: Appointment information


