DELETE FROM users;
DELETE FROM account_types;

INSERT INTO account_types VALUES
(1, 'ADMIN'),
(2, 'USER'),
(3, 'BLOCKED');

INSERT INTO users VALUES
(0, 'kirillmisnik', 'df25d28aa4082c5ed90f0b5e735b8ec3', 'Kirill', 'Misnik', 'I am a Java junior developer.', '+7 (985) 123-45-67', 'kirill.misnik.g@gmail.com', 1, '2021-06-28 10:23:54', '2021-06-28 13:39:23', '2021-06-28 14:00:07');