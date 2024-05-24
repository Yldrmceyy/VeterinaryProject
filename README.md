# Veterinary Management System

This project is an API developed to manage the business processes of a veterinary clinic. The system will be used by veterinary staff and includes doctor, customer, and animal management, vaccine records, and appointment tracking.

## Contents

- [Technologies Used](#technologies-used)
- [Project Architecture](#project-architecture)
- [Database Schema](#database-schema)
- [Setup](#setup)
- [API Usage](#api-usage)



## Technologies Used

<code><img src="https://skillicons.dev/icons?i=spring" height="40" alt="spring logo"/>
<code><img src="https://skillicons.dev/icons?i=postgres" height="40" alt="postgresql logo"/>
<code><img src="https://skillicons.dev/icons?i=java" height="40" alt="java logo"/>
<code><img src="https://skillicons.dev/icons?i=github" height="40" alt="github logo"/>
<code><img src="https://skillicons.dev/icons?i=git" height="40" alt="git logo"/>
<code><img src="https://skillicons.dev/icons?i=postman" height="40" alt="postman logo"/>
<code><img src="https://skillicons.dev/icons?i=idea" height="40" alt="intellijidea logo"/>
<code><img src="https://skillicons.dev/icons?i=maven" height="40" alt="apachemaven logo"/>
<code><img width="46" src="https://user-images.githubusercontent.com/25181517/192107858-fe19f043-c502-4009-8c47-476fc89718ad.png" alt="REST" title="REST"/></code>
<code><img width="46" src="https://user-images.githubusercontent.com/25181517/186711335-a3729606-5a78-4496-9a36-06efcc74f800.png" alt="Swagger" title="Swagger"/></code>

## Project Architecture


The project follows a layered architecture. The main package structure is as follows:

- `controller`: API endpoints
- `business`: Business logic
- `repository`: Database operations
- `dto`: Data Transfer Objects (Request and Response)
- `entities`: Database entities
- `core`: Utility classes and configurations

## Database Schema

The database schema includes the following tables:

- `customers`: Customer information
- `animals`: Animal information
- `vaccines`: Vaccine information
- `doctors`: Doctor information
- `available_dates`: Doctor availability dates
- `appointments`: Appointment information


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
### Swagger Screenshots

## API Endpoints

| Endpoint                                       | HTTP Method | Description                                             |
|------------------------------------------------|-------------|---------------------------------------------------------|
| **Vaccine Operations**                         |             |                                                         |
| /v1/vaccines                                   | POST        | Create a new vaccine record.                            |
| /v1/vaccines                                   | GET         | Retrieve all vaccine records.                           |
| /v1/vaccines                                   | PUT         | Update an existing vaccine record.                      |
| /v1/vaccines/{id}                              | DELETE      | Delete a specific vaccine record.                       |
| /v1/vaccines/animal/{id}                       | GET         | Retrieve all vaccine records for a specific animal.     |
| /v1/vaccines/findByDate                        | GET         | Retrieve vaccine records for a specific date range.     |
| **Doctor Operations**                          |             |                                                         |
| /v1/doctors                                    | POST        | Create a new doctor record.                             |
| /v1/doctors                                    | PUT         | Update an existing doctor record.                       |
| /v1/doctors                                    | GET         | Retrieve all doctor records.                            |
| /v1/doctors/{id}                               | DELETE      | Delete a specific doctor record.                        |
| **Customer Operations**                        |             |                                                         |
| /v1/customers                                  | POST        | Create a new customer record.                           |
| /v1/customers                                  | PUT         | Update an existing customer record.                     |
| /v1/customers                                  | GET         | Retrieve all customer records.                          |
| /v1/customers/{id}                             | DELETE      | Delete a specific customer record.                      |
| /v1/customers/{name}                           | GET         | Retrieve customer records with a specific name.         |
| **Available Date Operations**                  |             |                                                         |
| /v1/available-dates                            | POST        | Create a new available date record.                     |
| /v1/available-dates                            | PUT         | Update an existing available date record.               |
| /v1/available-dates                            | GET         | Retrieve all available date records.                    |
| /v1/available-dates/{id}                       | DELETE      | Delete a specific available date record.               |
| **Appointment Operations**                     |             |                                                         |
| /v1/appointments                               | POST        | Create a new appointment record.                        |
| /v1/appointments                               | GET         | Retrieve all appointment records.                       |
| /v1/appointments                               | PUT         | Update an existing appointment record.                 |
| /v1/appointments/{id}                          | DELETE      | Delete a specific appointment record.                   |
| /v1/appointments/filterByDoctorDate/{doctorId}-{findByDate} | GET | Filter appointments by a specific doctor and date range. |
| /v1/appointments/filterByAnimalDate/{animalId}-{findByDate} | GET | Filter appointments by a specific animal and date range. |
| **Animal Operations**                          |             |                                                         |
| /v1/animals                                    | POST        | Create a new animal record.                             |
| /v1/animals                                    | GET         | Retrieve all animal records.                            |
| /v1/animals                                    | PUT         | Update an existing animal record.                      |
| /v1/animals/{id}                               | DELETE      | Delete a specific animal record.                        |
| /v1/animals/{name}                             | GET         | Retrieve animal records with a specific name.           |
| /v1/animals/customer/{id}                      | GET         | Retrieve all animal records for a specific customer.    |



