CREATE USER 'lol'@'localhost' IDENTIFIED BY 'lol';
CREATE USER 'lol'@'%' IDENTIFIED BY 'lol';

GRANT ALL PRIVILEGES ON *.* TO 'lol'@'localhost';
GRANT ALL PRIVILEGES ON *.* TO 'lol'@'%';

CREATE
    DATABASE lol DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
