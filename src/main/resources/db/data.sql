INSERT INTO
    `User` (`id`, `username`, `password`, `created_at`, `modified_at`)
VALUES
    (1, 'user1', '{noop}password1', '2025-06-18T23:24', null),
    (2, 'user2', '{noop}password2', '2025-06-18T16:30', null);

INSERT INTO
    `Article` (`id`, `user_id`, `title`, `content`, `created_at`, `modified_at`)
VALUES
    (1, 1, '첫 번째 글', '안녕하세요. 이것은 첫 번째 게시글입니다.', '2025-06-17T10:24', null),
    (2, 1, '두 번째 글', '테스트 게시물입니다.', '2025-06-18T12:45', null),
    (3, 2, 'test2의 글', '두 번째 유저가 작성한 글입니다.', '2025-06-16T08:22', null);

INSERT INTO `Comment` (`id`, `user_id`, `article_id`, `parent_id`, `comment`, `is_deleted`, `created_at`, `modified_at`)
VALUES
    (1, 2, 1, NULL, '첫 번째 글 잘 봤어요!', 0, '2025-06-18T14:00', '2025-06-18T14:00'),
    (2, 1, 1, NULL, '감사합니다!', 0, '2025-06-18T14:10', '2025-06-18T14:10'),
    (3, 1, 1, 1, '좋게 봐주셔서 감사해요!', 0, '2025-06-18T14:15', '2025-06-18T14:15'),
    (4, 2, 1, 2, '별말씀을요~', 0, '2025-06-18T14:20', '2025-06-18T14:20'),
    (5, 2, 3, NULL, '이건 제 글입니다.', 0, '2025-06-18T14:30', '2025-06-18T14:30');
