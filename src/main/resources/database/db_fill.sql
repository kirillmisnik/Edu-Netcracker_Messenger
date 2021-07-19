DELETE FROM messages;
DELETE FROM chat_members;
DELETE FROM users;
DELETE FROM chats;

INSERT INTO users VALUES
(1, 'kirillmisnik', '$2a$10$/1ibOuesi8D3l9MBabqHYuUFeCJcUqz0/owvGeKqL0pez0etUAyPm', 'Kirill', 'Misnik', 'I am a Java junior developer.', '+7 (985) 123-45-67', 'kirill.misnik.g@gmail.com', null, 'ADMIN', '2021-06-28 10:23:54', '2021-07-09 18:54:11', '2021-07-11 10:23:42'),
(2, 'johnsmith', '$2a$10$/1ibOuesi8D3l9MBabqHYuUFeCJcUqz0/owvGeKqL0pez0etUAyPm', 'Xavier', 'Gardner', 'I am a sample user.', '+7 (183) 707-20-61', 'x.xavier@gmail.com', null, 'USER', '2021-06-25 12:41:35', '2021-07-12 21:25:32', '2021-07-13 08:37:47'),
(3, 'watsonroth', '$2a$10$/1ibOuesi8D3l9MBabqHYuUFeCJcUqz0/owvGeKqL0pez0etUAyPm', 'Roth', 'Watson', 'I am a sample user.', '+7 (922) 815-93-45', 'roth.watson@gmail.com', null, 'USER', '2021-07-02 21:31:41', '2021-07-16 06:39:01', '2021-07-18 01:00:46'),
(4, 'ivan97', '$2a$10$/1ibOuesi8D3l9MBabqHYuUFeCJcUqz0/owvGeKqL0pez0etUAyPm', 'Ivan', 'Vang', 'I am a sample user.', '+7 (364) 995-33-54', 'ivan.1g@gmail.com', null, 'USER', '2021-06-25 00:18:39', '2021-07-21 03:40:57', '2021-07-25 09:58:37'),
(5, 'bobdylan', '$2a$10$/1ibOuesi8D3l9MBabqHYuUFeCJcUqz0/owvGeKqL0pez0etUAyPm', 'Bob', 'Dylan', 'I am a sample user.', '+7 (116) 308-66-15', 'bob.dylan@gmail.com', null, 'USER', '2021-06-29 02:17:10', '2021-07-28 04:45:49', '2021-07-30 13:07:36'),
(6, 'marshall', '$2a$10$/1ibOuesi8D3l9MBabqHYuUFeCJcUqz0/owvGeKqL0pez0etUAyPm', 'Marshall', 'Chapman', 'I am a sample user.', '+7 (303) 387-94-96', 'mar.shall@gmail.com', null, 'USER', '2021-06-30 06:17:41', '2021-07-11 10:02:11', '2021-07-13 08:08:05');

INSERT INTO chats VALUES
(1, 'PERSONAL', '2021-06-28 10:23:54', null, null),
(2, 'PERSONAL', '2021-08-21 11:20:45', null, null),
(3, 'GROUP', '2021-06-28 10:23:54', 'Test group chat', null),
(4, 'PERSONAL', '2021-06-28 10:23:54', null, null);

INSERT INTO chat_members VALUES
(1, 1, 1),
(2, 1, 2),
(3, 2, 1),
(4, 2, 3),
(5, 3, 1),
(6, 3, 2),
(7, 3, 3),
(8, 4, 2),
(9, 4, 3);

INSERT INTO messages VALUES
(1, 1, 2, 'Привет! Как дела?', null, '2021-06-28 10:23:54', '2021-06-28 10:23:54'),
(2, 1, 1, 'Хорошо, а у тебя?', null, '2021-06-28 10:23:59', '2021-06-28 10:23:59'),
(3, 1, 2, 'Отлично!', null, '2021-06-28 10:24:01', '2021-06-28 10:24:01'),
(4, 1, 2, 'Как успехи с мессенджером?', null, '2021-06-28 10:24:54', '2021-06-28 10:24:54'),
(5, 1, 1, 'Справляяюсь, спасибо!', null, '2021-06-28 10:25:00', null),
(6, 1, 2, 'Рад слышать, удачи!', null, '2021-06-28 10:25:32', null),
(7, 3, 1, 'Привет, сделал новый групповой чат!', null, '2021-06-28 10:24:54', '2021-06-28 10:24:54'),
(8, 3, 2, 'Отлично, спасибо!', null, '2021-06-28 10:25:00', null),
(9, 3, 3, 'Здорово, будем пользоваться', null, '2021-06-28 10:25:00', null);

-- INSERT INTO group_chats VALUES
-- (1, 10, '2021-06-28 10:23:54', 'test chat', null);