# 일정 관리 앱 프로젝트
## ERD
![Todo ERD](https://github.com/user-attachments/assets/ef15c018-a0b0-4d76-838a-581b17e53145)

## CRUD 명세
### POST
- url `http://localhost:8080/todo-list`

| key  | value type |
| --- | --- | 
| title | varchar | 
| description | varchar|
| username | varchar|
| password | varchar|

- Request example
```json
{
    "title" : "첫번째 할일",
    "description" : "API 설계 및 ERD 작성",
    "username":"ziy0ung",
    "password": "12345678"
}
```

- 201 Response example

```json
{
    "id": 1,
    "username": "ziy0ung",
    "title": "첫번째 할일",
    "description": "API 설계 및 ERD 작성",
    "createdAt": "2025-11-04T07:50:08.207158",
    "modifiedAt": "2025-11-04T07:50:08.207158"
}
```

- 400 Response example
```json
{
    "code": "VALIDATION_ERROR",
    "message": "Request Body를 확인해 주세요."
}
{
    "code": "VALIDATION_ERROR",
    "message": "username값이 누락되었습니다."
}
{
    "code": "VALIDATION_ERROR",
    "message": "password값이 누락되었습니다."
}
```

### GET (단건)
- url `http://localhost:8080/todo-list/{todoId}`
  - path parameter type : int

- 200 Response example
```json
{
    "id": 1,
    "username": "ziy0ung",
    "title": "첫번째 할일",
    "description": "API 설계 및 ERD 작성",
    "createdAt": "2025-11-04T07:50:08.207158",
    "modifiedAt": "2025-11-04T07:50:08.207158"
}
```

- 400 Response example
```json
{
    "code": "BAD_REQUEST",
    "message": "존재하지 않는 글 입니다."
}
```

### GET (다건)
- 전체 조회 (내림차순)
   - url `http://localhost:8080/todo-list`

- 200 Response example
```json
[
    {
        "id": 3,
        "username": "ziho",
        "title": "세번째 할일",
        "description": "일정 조회 기능 만들기",
        "createdAt": "2025-11-03T21:38:45.729139",
        "modifiedAt": "2025-11-03T21:38:45.729139"
    },
    {
        "id": 2,
        "username": "ziho",
        "title": "두번째 할일",
        "description": "일정 생성 기능 만들기",
        "createdAt": "2025-11-03T21:38:26.106643",
        "modifiedAt": "2025-11-03T21:38:26.106643"
    },
    {
        "id": 1,
        "username": "ziy0ung",
        "title": "첫번째 할일",
        "description": "API 설계 및 ERD 작성",
        "createdAt": "2025-11-04T07:50:08.207158",
        "modifiedAt": "2025-11-04T07:50:08.207158"
}
    
]
```


- 특정 username 필터 조회 (내림차순)
    - url` http://localhost:8080/todo-list?username=`

query parameter
| key  | value type |
| --- | --- | 
| username | varchar | 

- 200 Response example
```json
[
    {
        "todoId": 3,
        "username": "ziho",
        "title": "세번째 할일",
        "description": "일정 조회 기능 만들기",
        "createdAt": "2025-11-03T21:38:45.729139",
        "modifiedAt": "2025-11-03T21:38:45.729139"
    },
    {
        "todoId": 2,
        "username": "ziho",
        "title": "두번째 할일",
        "description": "일정 생성 기능 만들기",
        "createdAt": "2025-11-03T21:38:26.106643",
        "modifiedAt": "2025-11-03T21:38:26.106643"
    }
]
```
### PATCH
- url `http://localhost:8080/todo-list/{todoId}`
  - path parameter type : int

| key  | value type |
| --- | --- | 
| title | varchar | 
| username | varchar|
- 둘중 하나의 key,value만 들어와도 가능

- Request example
```json
{
    "title": "첫번째 할일 수정함",
    "username": "jay",
    "password": "12345678"
}
```

- 200 Response example

```json
{
    "id": 4,
    "title": "첫번째 할일 수정함",
    "username": "jay",
    "modifiedAt": "2025-11-04T07:50:08.207158"
}
```
- 400 Response example
```json
{
    "code": "BAD_REQUEST",
    "message": "비밀번호가 일치하지 않습니다."
}

```

### DELETE
- url `http://localhost:8080/todo-list/{todoId}`
   - path parameter type : int

  
- Request example

```json
{
    "password" : "12345678"
}

```

- 204 Response

- 400 Response example
```json
{
    "code": "BAD_REQUEST",
    "message": "비밀번호가 일치하지 않습니다."
}
{
    "code": "BAD_REQUEST",
    "message": "존재하지않는 글입니다."
}
```
