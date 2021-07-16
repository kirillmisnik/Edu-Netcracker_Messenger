DELETE FROM users;
DELETE FROM account_types;

INSERT INTO account_types VALUES
(1, 'ADMIN'),
(2, 'USER'),
(3, 'BLOCKED');

INSERT INTO users VALUES
(1, 'kirillmisnik', '$2a$10$/1ibOuesi8D3l9MBabqHYuUFeCJcUqz0/owvGeKqL0pez0etUAyPm', 'Kirill', 'Misnik', 'I am a Java junior developer.', '+7 (985) 123-45-67', 'kirill.misnik.g@gmail.com', 1, '2021-06-28 10:23:54', '2021-06-28 13:39:23', '2021-06-28 14:00:07'),
(2, 'johnsmith', '$2a$10$/1ibOuesi8D3l9MBabqHYuUFeCJcUqz0/owvGeKqL0pez0etUAyPm', 'John', 'Smith', 'I am a sample user.', '+7 (999) 123-45-67', 'johnsmith@gmail.com', 2, '2021-06-28 10:23:54', '2021-06-28 13:39:23', '2021-06-28 14:00:07');