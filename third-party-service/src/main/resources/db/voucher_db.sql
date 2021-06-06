ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'p@ssword';
FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS voucher CHARACTER SET utf8mb3 COLLATE utf8_unicode_ci;

USE voucher;

CREATE TABLE IF NOT EXISTS  `voucher` (
   `id` int NOT NULL AUTO_INCREMENT,
    `status` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
    `telco` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
    `price` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
    `data_plan` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
    `code` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
    `created_date` datetime(3) DEFAULT NULL,
    `updated_date` datetime(3) DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;


CREATE TABLE IF NOT EXISTS `errors` (
    `id` int NOT NULL AUTO_INCREMENT,
    `error_code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
    `error_detail` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
    `language` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;
