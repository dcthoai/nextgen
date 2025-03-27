USE `nextgen_brand`;

CREATE TABLE company (
    `ID` INT AUTO_INCREMENT PRIMARY KEY,
    `logo` VARCHAR(255) NOT NULL,
    `image` VARCHAR(255) NOT NULL,
    `video_intro` VARCHAR(255),
    `license_code` VARCHAR(20),
    `license_date` VARCHAR(10),
    `license_address` VARCHAR(255),
    `name` VARCHAR(100) NOT NULL,
    `description` VARCHAR(500),
    `address` VARCHAR(255) NOT NULL,
    `phone` VARCHAR(20) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    `website` VARCHAR(100),
    `map` TEXT NOT NULL,
    `map_image` VARCHAR(255) NOT NULL,
    `map_slide_text` VARCHAR(25),
    `facebook` VARCHAR(255),
    `instagram` VARCHAR(255),
    `zalo` VARCHAR(255),
    `youtube` VARCHAR(255),
    `created_by` VARCHAR(45) NOT NULL DEFAULT 'SYSTEM',
    `created_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_modified_by` VARCHAR(45) DEFAULT 'SYSTEM',
    `last_modified_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE banner (
    `ID` INT AUTO_INCREMENT PRIMARY KEY,
    `text_stroke_1` VARCHAR(45),
    `text_stroke_2` VARCHAR(45),
    `text_uppercase_1` VARCHAR(45),
    `text_uppercase_2` VARCHAR(45),
    `image` VARCHAR(255),
    `position` VARCHAR(45) NOT NULL UNIQUE,
    `created_by` VARCHAR(45) NOT NULL DEFAULT 'SYSTEM',
    `created_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `last_modified_by` VARCHAR(45) DEFAULT 'SYSTEM',
    `last_modified_date` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
