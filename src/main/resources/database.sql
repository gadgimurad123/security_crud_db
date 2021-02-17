# CREATE TABLE my_security.table_users
# (
#     id        bigint       NOT NULL AUTO_INCREMENT PRIMARY KEY,
#     username  varchar(255) NOT NULL,
#     password  varchar(255) NOT NULL,
#     name      varchar(255) NOT NULL,
#     last_name varchar(255) NOT NULL,
#     age       int          NOT NULL,
#     email     varchar(255) NOT NULL
# ) ENGINE = InnoDB;
#
# CREATE TABLE my_security.table_roles
# (
#     id    bigint       NOT NULL AUTO_INCREMENT PRIMARY KEY,
#     roles varchar(255) NOT NULL
# ) ENGINE = InnoDB;
#
# CREATE TABLE my_security.table_user_role
# (
#     user_id bigint NOT NULL,
#     role_id bigint NOT NULL,
#     PRIMARY KEY (user_id, role_id),
#     FOREIGN KEY (user_id) REFERENCES my_security.table_users (id),
#     FOREIGN KEY (role_id) REFERENCES my_security.table_roles (id)
# ) ENGINE = InnoDB;

# INSERT INTO table_users value (35, 'admin', 'admin', 'admin', 'adminov', 20, 'admin@gmail.com');
# INSERT INTO table_users value (36, 'user', 'user', 'user', 'userov', 25, 'user@gmail.com');
# INSERT INTO table_users value (37, 'ivan', 'ivan', 'ivan', 'ivanov', 30, 'ivan@gmail.com');
#
# # INSERT INTO table_roles VALUES (1, 'ROLE_ADMIN');
# # INSERT INTO table_roles VALUES (2, 'ROLE_USER');
#
# INSERT INTO table_user_role value (4, 1);
# INSERT INTO table_user_role value (5, 2);
# INSERT INTO table_user_role value (37, 1);
