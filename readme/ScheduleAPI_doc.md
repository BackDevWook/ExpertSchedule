# Schedule

## 1. 일정 생성

### 요청
- **Method:** `POST`
- **URL:** `/schedules/cl`
- **Headers:**
    - `Content-Type: application/json`
- **Body:** (JSON)
  ```json
  {
    "title": "회의 일정",
    "content": "팀 회의 진행"
  }
  ```
- **Session Attribute:** `LOGIN_ID`

### 응답
- **Status:** `201 Created`
- **Body:** `"일정 생성 됌"`

---

## 2. 일정 단일 조회

### 요청
- **Method:** `GET`
- **URL:** `/schedules/{id}`
- **Path Variable:**
    - `id` (Long) - 조회할 일정 ID

### 응답
- **Status:** `200 OK`
- **Body:** (JSON)
  ```json
  {
    "userName": "사용자",
    "title": "회의 일정",
    "content": "팀 회의 진행",
    "createDateTime": "2024-04-02T10:00:00",
    "updateDateTime": "2024-04-02T12:00:00"
  }
  ```

---

## 3. 일정 전체 조회 (페이징)

### 요청
- **Method:** `GET`
- **URL:** `/schedules`
- **Query Parameters:**
    - `size` (int, 기본값: 5) - 페이지 크기
    - `sort` (String, 기본값: `userName,desc`) - 정렬 기준

### 응답
- **Status:** `200 OK`
- **Body:** (JSON)
  ```json
  {
    "content": [
      {
        "userName": "사용자",
        "title": "회의 일정",
        "content": "팀 회의 진행",
        "createDateTime": "2024-04-02T10:00:00",
        "updateDateTime": "2024-04-02T12:00:00"
      }
    ],
    "pageable": { ... },
    "totalPages": 10,
    "totalElements": 50
  }
  ```

---

## 4. 일정 수정

### 요청
- **Method:** `PATCH`
- **URL:** `/schedules/cl/{id}`
- **Path Variable:**
    - `id` (Long) - 수정할 일정 ID
- **Headers:**
    - `Content-Type: application/json`
- **Body:** (JSON)
  ```json
  {
    "title": "수정된 회의 일정",
    "content": "회의 일정 변경됨"
  }
  ```
- **Session Attribute:** `LOGIN_ID`

### 응답
- **Status:** `200 OK`
- **Body:** `"잘 수정 됌"`

---

## 5. 일정 삭제

### 요청
- **Method:** `DELETE`
- **URL:** `/schedules/cl/{id}`
- **Path Variable:**
    - `id` (Long) - 삭제할 일정 ID
- **Session Attribute:** `LOGIN_ID`

### 응답
- **Status:** `200 OK`
- **Body:** `"잘 삭제됌"`

---

## 에러 응답 예시

### 1. 인증되지 않은 사용자 (`401 Unauthorized`)
```json
{
  "error": "로그인 하셈"
}
```

### 2. 일정이 존재하지 않는 경우 (`404 Not Found`)
```json
{
  "error": "해당 스케줄 없음"
}
```