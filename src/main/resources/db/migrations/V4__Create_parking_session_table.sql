-- V4__Create_parking_session_table.sql
CREATE TABLE IF NOT EXISTS t_parking_session (
                                                 id BIGINT AUTO_INCREMENT PRIMARY KEY,
                                                 licence_place VARCHAR(255) NOT NULL,
                                                 start_time DATETIME NOT NULL,
                                                 end_time DATETIME,
                                                 parking_zone VARCHAR(40) NOT NULL,
                                                 cost DOUBLE DEFAULT 0.0,
                                                 user_id BIGINT NOT NULL,
                                                 FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
