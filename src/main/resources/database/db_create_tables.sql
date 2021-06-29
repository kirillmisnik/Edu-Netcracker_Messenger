CREATE TABLE IF NOT EXISTS account_types
(
    account_type_id SERIAL NOT NULL UNIQUE,
    account_type VARCHAR(80) NOT NULL,
    PRIMARY KEY (account_type_id)
);

CREATE TABLE IF NOT EXISTS users
(
    user_id BIGSERIAL NOT NULL UNIQUE,
    username VARCHAR(80) NOT NULL UNIQUE,
    password VARCHAR(80) NOT NULL UNIQUE,
    first_name VARCHAR(80) NOT NULL,
    last_name VARCHAR(80) NOT NULL,
    bio VARCHAR(210),
    phone_number VARCHAR(80) NOT NULL UNIQUE,
    email VARCHAR(80) UNIQUE,
    account_type_id SERIAL NOT NULL,
    account_creation_date TIMESTAMP NOT NULL,
    last_login_date TIMESTAMP,
    last_online_date TIMESTAMP,
    PRIMARY KEY (user_id),
    FOREIGN KEY (account_type_id)
    REFERENCES account_types(account_type_id)
);

CREATE TABLE IF NOT EXISTS chat_accesses
(
    chat_access_id SERIAL NOT NULL UNIQUE,
    chat_access VARCHAR(80) NOT NULL,
    PRIMARY KEY (chat_access_id)
);

CREATE TABLE IF NOT EXISTS chat_types
(
    chat_type_id SERIAL NOT NULL UNIQUE,
    chat_type VARCHAR(80) NOT NULL,
    PRIMARY KEY (chat_type_id)
);

CREATE TABLE IF NOT EXISTS personal_chats
(
    chat_id BIGSERIAL NOT NULL UNIQUE,
    chat_members_id VARCHAR(80) NOT NULL,
    chat_type_id SERIAL NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    chat_name VARCHAR(80) NOT NULL,
    PRIMARY KEY (chat_id),
    FOREIGN KEY (chat_type_id)
    REFERENCES chat_types(chat_type_id)
);

CREATE TABLE IF NOT EXISTS group_chats
(
    chat_id BIGSERIAL NOT NULL UNIQUE,
    chat_members_id VARCHAR(80) NOT NULL,
    chat_type_id SERIAL NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    chat_name VARCHAR(80) NOT NULL,
    chat_access_id SERIAL NOT NULL,
    chat_photo_id BIGSERIAL NOT NULL,
    PRIMARY KEY (chat_id),
    FOREIGN KEY (chat_access_id)
    REFERENCES chat_accesses(chat_access_id),
    FOREIGN KEY (chat_type_id)
    REFERENCES chat_types(chat_type_id)
);

CREATE TABLE IF NOT EXISTS messages
(
    message_id BIGSERIAL NOT NULL UNIQUE,
    chat_id BIGSERIAL NOT NULL,
    user_id BIGSERIAL NOT NULL,
    text VARCHAR(1000) NOT NULL,
    attachment_id BIGSERIAL,
    attachment_filename VARCHAR(80),
    creation_date TIMESTAMP NOT NULL,
    read_date TIMESTAMP,
    PRIMARY KEY (message_id),
    FOREIGN KEY (user_id)
    REFERENCES users(user_id),
    FOREIGN KEY (chat_id)
    REFERENCES personal_chats(chat_id),
    FOREIGN KEY (chat_id)
    REFERENCES group_chats(chat_id)
);