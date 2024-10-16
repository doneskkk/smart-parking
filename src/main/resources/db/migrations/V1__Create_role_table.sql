-- V1__Create_role_table.sql
CREATE TABLE IF NOT EXISTS t_role (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      name VARCHAR(50) NOT NULL UNIQUE
);