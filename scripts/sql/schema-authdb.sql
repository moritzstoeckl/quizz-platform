-- Create users table
CREATE TABLE IF NOT EXISTS users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    isDeactivated BOOLEAN NOT NULL
    );

-- Create sessions table
CREATE TABLE IF NOT EXISTS sessions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    jwtToken VARCHAR(255) UNIQUE NOT NULL,
    user_id BIGINT NOT NULL,
    isLoggedIn BOOLEAN NOT NULL,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
    );
