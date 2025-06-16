CREATE TABLE IF NOT EXISTS `User` (
    `id` bigint PRIMARY KEY,
    `username` varchar(20) COMMENT '유저명',
    `password` varchar(100) COMMENT '비밀번호',
    `created_at` timestamp COMMENT '계정 생성시간'
);

CREATE TABLE IF NOT EXISTS `Article` (
    `id` bigint PRIMARY KEY,
    `user_id` bigint,
    `title` varchar(100) COMMENT '제목',
    `content` varchar(500) COMMENT '본문',
    `created_at` timestamp COMMENT '작성 시간'
);

CREATE TABLE IF NOT EXISTS `Status_Code` (
    `code` varchar(50) PRIMARY KEY COMMENT '상태코드명',
    `type` varchar(10) COMMENT '상태코드 구분',
    `httpStatus` int COMMENT 'Http 응답코드',
    `message` varchar(20) COMMENT '상태코드 메시지'
);

ALTER TABLE `Article` ADD FOREIGN KEY (`user_id`) REFERENCES `User` (`id`);
