CREATE TABLE IF NOT EXISTS `books` (
    `id` bigint NOT NULL AUTO_INCREMENT,
    `title` varchar(50) NOT NULL,
    `categoryId` bigint NOT NULL,
    `category` varchar(50) NOT NULL,
    `description` varchar(500) NOT NULL,
    `price` double NOT NULL,
    `rent` double NOT NULL,
    `image` varchar(500) NOT NULL,
    `author` varchar(50) NOT NULL,
    `author_mobile` varchar(50) NULL,
    `author_email` varchar(50) NULL,
    `version` varchar(10) NOT NULL,
    `status` tinyint(1) DEFAULT '1',
    `created_at` datetime(6) NOT NULL,
    `updated_at` datetime(6) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;