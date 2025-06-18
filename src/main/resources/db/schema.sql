CREATE TABLE IF NOT EXISTS `User` (
    `id` bigint PRIMARY KEY AUTO_INCREMENT,
    `username` varchar(20) NOT NULL COMMENT '유저명',
    `password` varchar(100) NOT NULL COMMENT '비밀번호',
    `created_at` timestamp NOT NUll COMMENT '계정 생성시간',
    `modified_at` timestamp COMMENT '계정 수정시간'
);

CREATE TABLE IF NOT EXISTS `Article` (
    `id` bigint PRIMARY KEY AUTO_INCREMENT,
    `user_id` bigint NOT NULL,
    `title` varchar(100) NOT NULL COMMENT '제목',
    `content` varchar(500) COMMENT '본문',
    `created_at` timestamp NOT NULL COMMENT '게시글 작성 시간',
    `modified_at` timestamp COMMENT '게시글 수정 시간',
    CONSTRAINT fk_article_user
        FOREIGN KEY (`user_id`) REFERENCES `User`(`id`)
        ON DELETE RESTRICT
);

CREATE TABLE IF NOT EXISTS `Status_Code` (
    `code` varchar(50) PRIMARY KEY COMMENT '상태코드명',
    `type` varchar(10) COMMENT '상태코드 구분',
    `httpStatus` int COMMENT 'Http 응답코드',
    `message` varchar(50) COMMENT '상태코드 메시지'
);
