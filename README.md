# Edu-Netcracker_Messenger
Netcracker EDU Summer 2021 - Индивидуальный учебный проект

### Запуск проекта

`open db-create.sh` (для создания БД)

`mvn install`

`mvn spring-boot:run`

---
### UML - схема

<img alt="uml" src="media/UML.png">

### Схема базы данных

<img alt="db schema" src="media/DB_schema.png">

---
### API
#### User

🟡 `POST /api/registration` - регистрирует пользователя

Пример тела запроса:
```json
{
    "username": "johnsmith",
    "password": "john123", 
    "firstName": "John", 
    "lastName": "Smith", 
    "bio": "Sample user",
    "phoneNumber": "+7 (987) 123-45-67",
    "email": "johnsmith@gmail.com"
}
```

🟢 `GET /api/user/{id}` - возвращает информацию о пользователе

Пример ответа:
```json
{
    "id": 2,
    "username": "johnsmith",
    "firstName": "John",
    "lastName": "Smith",
    "bio": "I am a sample user.",
    "phoneNumber": "+7 (999) 123-45-67",
    "email": "johnsmith@gmail.com",
    "lastOnlineDate": "2021-06-28T14:00:07"
}
```

🟢 `GET /api/user/all` - возвращает данные по всем пользователям

Пример ответа:
```json
[
    {
        "id": 1,
        "username": "kirillmisnik",
        "firstName": "Kirill",
        "lastName": "Misnik",
        "bio": "I am a Java junior developer.",
        "lastOnlineDate": "2021-06-28T14:00:07"
    },
    {
        "id": 2,
        "username": "johnsmith",
        "firstName": "John",
        "lastName": "Smith",
        "bio": "I am a sample user.",
        "lastOnlineDate": "2021-06-28T14:00:07"
    }
]
```

🟢 `GET /api/user/{id}/chats` - возвращает список чатов, доступных пользователю

Пример ответа:
```json
[
    {
        "chatId": 1,
        "chatName": null,
        "chatPictureId": null,
        "lastMessage": "Рад слышать, удачи!"
    },
    {
        "chatId": 2,
        "chatName": null,
        "chatPictureId": null,
        "lastMessage": null
    },
    {
        "chatId": 3,
        "chatName": "Test group chat",
        "chatPictureId": null,
        "lastMessage": "Отлично, спасибо!"
    }
]
```

🔴 `DELETE /api/user/{id}` - удаляет пользователя по id

#### Chat

🟡 `POST /api/chat/create/{id}` - создает чат с пользователем

Пример тела запроса (персональный чат):
```json
{
    "chatMembersId": [6]
}
```
Пример тела запроса (групповой чат):
```json
{
    "chatMembersId": [2, 3],
    "chatName": "Test chat"
}
```

🟡 `POST /api/chat/{chatId}` - добавляет сообщение в чат

Пример тела запроса:
```json
{
    "text": "Привет!"
}
```

🟢 `GET /api/chat/{id}` - возвращает информацию о чате. Если чат персональный, то
название и иконка чата будут зависеть от пользователя, который делает запрос.

Пример ответа:
```json
{
    "id": 1,
    "type": "PERSONAL",
    "name": "John Smith",
    "members": [
        {
            "id": 1,
            "username": "kirillmisnik",
            "firstName": "Kirill",
            "lastName": "Misnik",
            "bio": "I am a Java junior developer.",
            "lastOnlineDate": "2021-07-11T10:23:42"
        },
        {
            "id": 2,
            "username": "johnsmith",
            "firstName": "John",
            "lastName": "Smith",
            "bio": "I am a sample user.",
            "lastOnlineDate": "2021-07-13T08:37:47"
        }
    ]
}
```

🟢 `GET /api/chat/{chatId}/messages/all` - возвращает все сообщения чата

Пример ответа:
```json
[
    {
        "chatId": 1,
        "senderId": 2,
        "text": "Привет! Как дела?",
        "attachmentId": null,
        "creationDate": "2021-06-28T10:23:54",
        "readDate": "2021-06-28T10:23:54"
    },
    {
        "chatId": 1,
        "senderId": 1,
        "text": "Хорошо, а у тебя?",
        "attachmentId": null,
        "creationDate": "2021-06-28T10:23:59",
        "readDate": "2021-06-28T10:23:59"
    }
]
```

🟢 `GET /api/chat/{chatId}/messages?page_size={ps}&page_num={pn}` - возвращает сообщения чата в виде страниц

Пример ответа:
```json
[
    {
        "chatId": 1,
        "senderId": 2,
        "text": "Привет! Как дела?",
        "attachmentId": null,
        "creationDate": "2021-06-28T10:23:54",
        "readDate": "2021-06-28T10:23:54"
    },
    {
        "chatId": 1,
        "senderId": 1,
        "text": "Хорошо, а у тебя?",
        "attachmentId": null,
        "creationDate": "2021-06-28T10:23:59",
        "readDate": "2021-06-28T10:23:59"
    }
]
```

🟢 `GET /api/chat/{chatId}/messages/new` - возвращает последнее непрочитанное сообщение чата

Пример ответа:
```json
{
    "chatId": 1,
    "senderId": 2,
    "text": "Рад слышать, удачи!",
    "attachmentId": null,
    "creationDate": "2021-06-28T10:25:32",
    "readDate": null
}
```

🔴 `DELETE /api/chat/{chatId}` - удаляет чат

#### Messages

🟡 `POST /api/message/1/read` - помечает сообщение (и все сообщение в этом чате до него) как "прочитанные"

🔴 `DELETE /api/message/1` - удаляет сообщение