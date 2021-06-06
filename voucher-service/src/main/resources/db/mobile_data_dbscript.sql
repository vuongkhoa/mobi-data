ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'p@ssword';
FLUSH PRIVILEGES;

CREATE DATABASE IF NOT EXISTS simcard CHARACTER SET utf8mb3 COLLATE utf8_unicode_ci;

USE simcard;

CREATE TABLE IF NOT EXISTS  `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `phone_number` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_date` datetime(3) DEFAULT NULL,
  `updated_date` datetime(3) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;


CREATE TABLE IF NOT EXISTS `md_order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `phone_number` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `voucher_code` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `created_date` datetime DEFAULT NULL,
  `updated_date` datetime DEFAULT NULL,
  `request_id` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `user_id` int NOT NULL,
  `price` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `data_plan` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `telco` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_id_idx` (`user_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;


CREATE TABLE IF NOT EXISTS `errors` (
    `id` int NOT NULL AUTO_INCREMENT,
    `error_code` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
    `error_detail` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
    `language` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;


CREATE TABLE IF NOT EXISTS `product` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `telco` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `data_plan` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `price` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `type` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `status` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3 COLLATE=utf8_unicode_ci;

LOCK TABLES `product` WRITE;
INSERT  IGNORE INTO `product` VALUES (1,'Viettel','2GB','200000','voucher','ACTIVE'),(2,'Viettel','3GB','300000','voucher','ACTIVE'),(3,'Viettel','5GB','500000','voucher','ACTIVE'),(4,'Vinaphone','2GB','200000','voucher','ACTIVE'),(5,'Vinaphone','3GB','300000','voucher','ACTIVE'),(6,'Vinaphone','5GB','500000','voucher','ACTIVE'),(7,'Mobifone','2GB','200000','voucher','ACTIVE'),(8,'Mobifone','3GB','300000','voucher','ACTIVE'),(9,'Mobifone','5GB','500000','voucher','ACTIVE');
UNLOCK TABLES;
