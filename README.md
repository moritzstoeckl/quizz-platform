# quizz-platform

## Environment Setup (For Development)

This project includes a pre-configured `.env` file with development-specific values.

### Important Notes:
- The `.env` file contains simple, non-sensitive data for development purposes only.
- **Do not use this `.env` file in production.**
- If you need to customize the environment variables for your own setup, feel free to modify the `.env` file locally without committing those changes.


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