INSERT INTO
    `User` (`id`, `username`, `password`, `created_at`)
VALUES
    (1, 'test_user1', 'password1', NOW()),
    (2, 'test_user2', 'password2', NOW());

INSERT INTO
    `Article` (`id`, `user_id`, `title`, `content`, `created_at`)
VALUES
    (1, 1, '첫 번째 글', '안녕하세요. 이것은 첫 번째 게시글입니다.', NOW()),
    (2, 1, '두 번째 글', '테스트 게시물입니다.', NOW()),
    (3, 2, 'test2의 글', '두 번째 유저가 작성한 글입니다.', NOW());

INSERT INTO
    `Status_Code` (`code`, `type`, `httpStatus`, `message`)
VALUES
    ('OK', 'SUCCESS', 200, '조회를 성공하였습니다.'),
    ('NOT_FOUND', 'ERROR', 404, '리소스를 찾을 수 없습니다.'),
    ('INTERNAL_ERROR', 'ERROR', 500, '서버 오류입니다.');