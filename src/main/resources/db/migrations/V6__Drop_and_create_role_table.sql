DROP TABLE user_roles;
DROP TABLE t_role;

CREATE TABLE IF NOT EXISTS t_role (
                                      id INT AUTO_INCREMENT PRIMARY KEY,
                                      name VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS user_roles (
                                          user_id BIGINT NOT NULL,
                                          role_id INT NOT NULL,
                                          PRIMARY KEY (user_id, role_id),
                                          FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
                                          FOREIGN KEY (role_id) REFERENCES t_role(id) ON DELETE CASCADE
);
