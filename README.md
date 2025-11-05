# 일정 관리 앱 프로젝트
## ERD
![Todo ERD](https://github.com/user-attachments/assets/d32ddd35-b1a5-4bdd-9253-03062f7a5b1f)

## API 명세
### POST 할일/댓글
- url `http://localhost:8080/todos`

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
    "message": "Request Body를 확인해 주세요.",
    "details": null
}
{
    "code": "VALIDATION_ERROR",
    "message": "유효성 검사 실패",
    "details": {
        "password": "비밀번호는 필수 값입니다.",
        "description": "일정 내용은 필수 값입니다.",
        "title": "크기가 0에서 30 사이여야 합니다",
        "username": "이름은 필수 값입니다."
    }
}
```
- url `http://localhost:8080//todos/{todoId}/comments`
    - path parameter type : int
 
- 201 Response example
```json
{
    "id": 1,
    "content": "첫 댓글",
    "username": "익명익명",
    "password": "12341234",
    "createdAt": "2025-11-04T20:14:53.166355",
    "modifiedAt": "2025-11-04T20:14:53.166355"
}
```
- 400 Response example
```json
{
    "code": "VALIDATION_ERROR",
    "message": "Request Body를 확인해 주세요.",
    "details": null
}
{
    "code": "VALIDATION_ERROR",
    "message": "유효성 검사 실패",
    "details": {
        "password": "비밀번호는 필수 값입니다.",
        "content": "댓글 내용은 필수 값입니다.",
        "username": "이름은 필수 값입니다."
    }
}
```

### GET (단건)
- url `http://localhost:8080/todos/{todoId}`
  - path parameter type : int

- 200 Response example
```json
{
    "id": 2,
    "username": "zisoo",
    "title": "2번째 할일",
    "description": " 일정 생성 기능 만들기",
    "createdAt": "2025-11-04T14:32:09.843154",
    "modifiedAt": "2025-11-04T14:32:09.843154",
    "comments": [
        {
            "id": 1,
            "username": "user1",
            "content": "나도 나도",
            "createdAt": "2025-11-04T14:34:23.417331",
            "modifiedAt": "2025-11-04T14:34:23.417331"
        },
        {
            "id": 2,
            "username": "user2",
            "content": "나는 아직",
            "createdAt": "2025-11-04T14:35:38.948047",
            "modifiedAt": "2025-11-04T14:35:38.948047"
        },
        {
            "id": 3,
            "username": "user3",
            "content": "나는 완료",
            "createdAt": "2025-11-04T14:35:40.546472",
            "modifiedAt": "2025-11-04T14:35:40.546472"
        }
    ]
}
```

- 400 Response example
```json
{
    "code": "BAD_REQUEST",
    "message": "존재하지 않는 글입니다.",
    "details": null
}
```

### GET (다건)
- 전체 조회 (내림차순)
   - url `http://localhost:8080/todos`

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
    - url` http://localhost:8080/todos?username=`

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
- url `http://localhost:8080/todos/{todoId}`
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
    "message": "비밀번호가 일치하지 않습니다.",
    "details": null
}

```

### DELETE
- url `http://localhost:8080/todos/{todoId}`
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
    "message": "비밀번호가 일치하지 않습니다.",
    "details": null
}
{
    "code": "BAD_REQUEST",
    "message": "존재하지않는 글입니다.",
    "details": null
}
```
