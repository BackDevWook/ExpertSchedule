
# User

### 1. 회원가입 
    - POST /user/register
   

- Request Body
```json
{
  "loginId": "string",
  "password": "string",
  "userName": "string",
  "email": "string"
}

```

- Response


- || 201 Created
```json
"회원가입 성공함 ㅊㅋ"
```
- || 400 Bad Request (유효성 검증 실패 시)

***
### 2. 로그인
    - POST /user/login
   

- Reqeust Body
```json
{
  "loginId": "string",
  "password": "string"
}
```
- Response


- || 201 Created
```json
"로그인 성공함 ㅊㅋ"
```
- || 401 Unauthorized
```json
"비밀번호 틀림;;"
```
***
### 3. 로그아웃
    - DELETE /user/cl/logout/{loginId}
    - Path Variable : loginId (String)

- Response 


-  || 200 OK
```json
"로그아웃 됌"
```
- 세션 및 쿠키 삭제

***

### 4.비밀번호 변경
    - PATCH /user/cl/{loginId}
    - Path Variable : loginId (String)

- Reqeust Body
```json
{
  "currentPassword": "string",
  "newPassword": "string"
}
```

- Response


- || 200 ok
```json
"비밀번호 변경 성공"
```
- 400 Bad Request : 유효성 검증 실패시
***

### 5.계정 삭제
    - DELETE /user/cl/{loginId}
    - Path Variable : loginId (String)

- Request Body
```json
{
  "password": "string"
}
```
- Response


- || 200 ok
```json
"잘 삭제 됌"
```
***

### 6.유저 정보 조회
    - GET /user/{loginId}
    - Path Variable : loginId (String)

- Response


- 200 ok
```json
{
  "loginId": "string",
  "userName": "string",
  "email": "string"
}
```
- 404 Not Found (유저가 없을 경우)
***
### 7.유저 전체 조회 (페이징 적용)
    - GET /user
    - Query Parameters (Optional)
        - page : 페이지 번호 (기본값 : 0)
        - size : 한 페이지에 보여줄 개수 (기본값 : 5)
        - sort : 정렬 기준 필드 (기본값: userName, DESC)
    
- Response


- || 200 ok
```json
{
  "content": [
    {
      "loginId": "string",
      "userName": "string",
      "email": "string"
    }
  ],
  "pageable": {
    "pageNumber": 0,
    "pageSize": 5
  },
  "totalElements": 10,
  "totalPages": 2
}

```


    
