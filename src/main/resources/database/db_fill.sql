DELETE FROM messages;
DELETE FROM users;
DELETE FROM personal_chats;

INSERT INTO users VALUES
(1, 'kirillmisnik', '$2a$10$/1ibOuesi8D3l9MBabqHYuUFeCJcUqz0/owvGeKqL0pez0etUAyPm', 'Kirill', 'Misnik', 'I am a Java junior developer.', '+7 (985) 123-45-67', 'kirill.misnik.g@gmail.com', 1, '2021-06-28 10:23:54', '2021-07-09 18:54:11', '2021-07-11 10:23:42'),
(2, 'johnsmith', '$2a$10$/1ibOuesi8D3l9MBabqHYuUFeCJcUqz0/owvGeKqL0pez0etUAyPm', 'Xavier', 'Gardner', 'I am a sample user.', '+7 (183) 707-20-61', 'x.xavier@gmail.com', 2, '2021-06-25 12:41:35', '2021-07-12 21:25:32', '2021-07-13 08:37:47'),
(3, 'watsonroth', '$2a$10$/1ibOuesi8D3l9MBabqHYuUFeCJcUqz0/owvGeKqL0pez0etUAyPm', 'Roth', 'Watson', 'I am a sample user.', '+7 (922) 815-93-45', 'roth.watson@gmail.com', 2, '2021-07-02 21:31:41', '2021-07-16 06:39:01', '2021-07-18 01:00:46'),
(4, 'ivan97', '$2a$10$/1ibOuesi8D3l9MBabqHYuUFeCJcUqz0/owvGeKqL0pez0etUAyPm', 'Ivan', 'Vang', 'I am a sample user.', '+7 (364) 995-33-54', 'ivan.1g@gmail.com', 2, '2021-06-25 00:18:39', '2021-07-21 03:40:57', '2021-07-25 09:58:37'),
(5, 'bobdylan', '$2a$10$/1ibOuesi8D3l9MBabqHYuUFeCJcUqz0/owvGeKqL0pez0etUAyPm', 'Bob', 'Dylan', 'I am a sample user.', '+7 (116) 308-66-15', 'bob.dylan@gmail.com', 2, '2021-06-29 02:17:10', '2021-07-28 04:45:49', '2021-07-30 13:07:36'),
(6, 'marshall', '$2a$10$/1ibOuesi8D3l9MBabqHYuUFeCJcUqz0/owvGeKqL0pez0etUAyPm', 'Marshall', 'Chapman', 'I am a sample user.', '+7 (303) 387-94-96', 'mar.shall@gmail.com', 2, '2021-06-30 06:17:41', '2021-07-11 10:02:11', '2021-07-13 08:08:05');

INSERT INTO personal_chats VALUES
(1, 1, 2, '2021-06-28 10:23:54', 'John Smith');

INSERT INTO messages VALUES
(1, 1, 2, 'Привет! Как дела?', 32, 'photo', '2021-06-28 10:23:54', '2021-06-28 10:23:54'),
(2, 1, 1, 'Хорошо, а у тебя?', 33, 'photo', '2021-06-28 10:23:54', '2021-06-28 10:23:54');