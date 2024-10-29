# quizz-platform

## Environment Setup (For Development)

### Environment Variables

1. Copy the `.env.example` file to create your own `.env` file:

   ```bash
   cp .env.example .env

## Database Initialization
   1. Start Docker with the necessary database containers.

   2. Manually create the auth_db database by logging into the database using the following commands:

      To login to auth_db, run:

      ``mysql -u auth_user -p -h 127.0.0.1 -P 3307 auth_db``

   3. Once logged in, execute the SQL scripts to create tables:

   ``source schema-authdb.sql;``

## How it works
 
- 2 Roles: Teacher and Student
- teachers can create course
- students can be enrolled in courses
- a course can have multiple quizzes
- a course has a result for each student based on quizzes
- a course ranks the results of students
- teachers create quizzes, courses
- students take courses
- Both roles can see results
- quizzes are multiple choice

## Technology

- Springboot/Hibernate
- Java 21
- Docker
- MySQL

## Swagger API Documentation

This project uses **Springdoc OpenAPI** to automatically generate interactive API documentation with **Swagger UI**.

### Accessing the Swagger UI

1. Ensure that the project dependencies are up to date by running:

   ```bash
   ./gradlew build
   
2. Start the Spring Boot application:

    ```bash
    ./gradlew bootRun

3. Once the application is running, you can access the Swagger UI in your browser at:
    ```bash
   http://localhost:8080/swagger-ui/index.html
This will open a web interface where you can interact with and test the API endpoints.

## Databases health
to check database health navigate to 
```bash
http://localhost:8080/actuator/health