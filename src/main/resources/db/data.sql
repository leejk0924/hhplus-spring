INSERT INTO
    `User` (`id`, `username`, `password`, `created_at`, `modified_at`)
VALUES
    (1, 'test_user1', 'password1', '2025-06-18T23:24', null),
    (2, 'test_user2', 'password2', '2025-06-18T16:30', null);

INSERT INTO
    `Article` (`id`, `user_id`, `title`, `content`, `created_at`, `modified_at`)
VALUES
    (1, 1, '첫 번째 글', '안녕하세요. 이것은 첫 번째 게시글입니다.', '2025-06-17T10:24', null),
    (2, 1, '두 번째 글', '테스트 게시물입니다.', '2025-06-18T12:45', null),
    (3, 2, 'test2의 글', '두 번째 유저가 작성한 글입니다.', '2025-06-16T08:22', null);