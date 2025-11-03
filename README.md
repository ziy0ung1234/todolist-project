# 일정 관리 앱 프로젝트
## ERD
![Todo ERD](https://github.com/user-attachments/assets/ef15c018-a0b0-4d76-838a-581b17e53145)

## CRUD 명세
### POST
url http://localhost:8080/todo-list

| key  | value type |
| --- | --- | 
| title | varchar | 
| description | varchar|

request
```json
{
    "title" : "일정",
    "description" : "할일",
    "userId": 1
}
```
response

201
```json
{
    "id": 1,
    "username": "지영",
    "title" : "제목",
    "description":"일정 내용",
    "created_at" : "2025-11-3 11:27",
    "updated_at" :  "2025-11-3 11:27"
}
```
400
```json
 {
  "error" : "~~~~~~"
}
```

### GET (단건)
url http://localhost:8080/todo-list/1
response

200
```json
{
    "id": 1,
    "name": "지영",
    "title" : "제목",
    "description":"일정 내용",
    "created_at" : "2025-11-3 11:27",
    "updated_at" :  "2025-11-3 11:27"
}
```

### GET (다건)
url http://localhost:8080/todo-list

200
```json
[{
    "id": 1,
    "name": "지영",
    "title" : "제목",
    "description":"일정 내용",
    "created_at" : "2025-11-3 11:27",
    "updated_at" :  "2025-11-3 11:27"
},{
    "id": 2,
    "name": "지영",
    "title" : "제목",
    "description":"일정 내용",
    "created_at" : "2025-11-3 11:27",
    "updated_at" :  "2025-11-3 11:27"
},{
    "id": 3,
    "name": "지영",
    "title" : "제목",
    "description":"일정 내용",
    "created_at" : "2025-11-3 11:27",
    "updated_at" :  "2025-11-3 11:27"
}]
```
### PUT
url http://localhost:8080/todo-list/1

| key  | value type |
| --- | --- | 
| title | varchar | 
| description | varchar|

request
```json
{
  "title" : "제목 수정",
  "description":"일정 내용 수정"
}
```

response

200
```json
{
    "id": 1,
    "name": "지영",
    "title" : "제목 수정",
    "description":"일정 내용 수정",
    "created_at" : "2025-11-3 11:27",
    "updated_at" :  "2025-11-3 11:35"
}
```


### DELETE
url http://localhost:8080/todo-list/1

response
204
