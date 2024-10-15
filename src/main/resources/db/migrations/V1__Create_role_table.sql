-- V1__Create_role_table.sql
CREATE TABLE IF NOT EXISTS t_role (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      name ENUM('role_admin', 'role_user') NOT NULL
);
