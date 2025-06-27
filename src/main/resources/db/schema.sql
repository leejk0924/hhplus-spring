CREATE TABLE IF NOT EXISTS `User` (
    `id` bigint PRIMARY KEY AUTO_INCREMENT,
    `username` varchar(20) NOT NULL COMMENT '유저명',
    `password` varchar(100) NOT NULL COMMENT '비밀번호',
    `role` varchar(20) NOT NULL COMMENT '권한',
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

CREATE TABLE IF NOT EXISTS `Comment` (
    `id` bigint PRIMARY KEY AUTO_INCREMENT,
    `user_id` bigint NOT NULL,
    `article_id` bigint(20) NOT NULL,
    `parent_id` bigint(20) DEFAULT NULL,
    `comment` varchar(255) COMMENT '댓글 본문',
    `is_deleted` TINYINT(1) DEFAULT 0 DEFAULT FALSE COMMENT '삭제 유무',
    `created_at` timestamp NOT NULL COMMENT '댓글 작성 시간',
    `modified_at` timestamp NOT NULL COMMENT '댓글 수정 시간',
    CONSTRAINT fk_comment_user
        FOREIGN KEY (`user_id`) REFERENCES `User`(`id`)
        ON DELETE RESTRICT,
    CONSTRAINT fk_comment_article
        FOREIGN KEY (`article_id`) REFERENCES `Article`(`id`)
        ON DELETE CASCADE,
    CONSTRAINT fk_comment_parentContent
        FOREIGN KEY (`parent_id`) REFERENCES `Comment`(`id`)
        ON DELETE CASCADE
)