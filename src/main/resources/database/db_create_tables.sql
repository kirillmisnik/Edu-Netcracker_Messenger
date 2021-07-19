CREATE TABLE IF NOT EXISTS chats
(
    chat_id BIGSERIAL NOT NULL UNIQUE,
    chat_type VARCHAR(80) NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    chat_name VARCHAR(80),
    chat_picture_id BIGINT,
    PRIMARY KEY (chat_id)
);

CREATE TABLE IF NOT EXISTS users
(
    user_id BIGSERIAL NOT NULL UNIQUE,
    username VARCHAR(80) NOT NULL UNIQUE,
    password VARCHAR(80) NOT NULL,
    first_name VARCHAR(80) NOT NULL,
    last_name VARCHAR(80) NOT NULL,
    bio VARCHAR(256),
    phone_number VARCHAR(80) NOT NULL UNIQUE,
    email VARCHAR(80) UNIQUE,
    profile_picture_id BIGINT,
    account_type VARCHAR(80) NOT NULL,
    account_creation_date TIMESTAMP NOT NULL,
    last_login_date TIMESTAMP,
    last_online_date TIMESTAMP,
    PRIMARY KEY (user_id)
);

CREATE TABLE IF NOT EXISTS chat_members
(
    chat_members_id BIGSERIAL NOT NULL UNIQUE,
    chat_id BIGSERIAL NOT NULL,
    user_id BIGSERIAL NOT NULL,
    PRIMARY KEY (chat_members_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (chat_id) REFERENCES chats(chat_id)
);

CREATE TABLE IF NOT EXISTS messages
(
    message_id BIGSERIAL NOT NULL UNIQUE,
    chat_id BIGSERIAL NOT NULL,
    user_id BIGSERIAL NOT NULL,
    text VARCHAR(1000) NOT NULL,
    attachment_id BIGINT,
    creation_date TIMESTAMP NOT NULL,
    read_date TIMESTAMP,
    PRIMARY KEY (message_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (chat_id) REFERENCES chats(chat_id)
);