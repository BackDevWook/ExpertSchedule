# 3. 댓글 기능

## 1. 댓글 전체 조회
### **GET** `/comments/{scheduleId}`
- **설명**: 특정 일정의 모든 댓글을 조회
- **요청 파라미터**:
    - `scheduleId` (Path Variable, Long) : 조회할 일정의 ID
- **응답 예시** (200 OK):
```json
[
  {
    "userName": "사용자1",
    "scheduleName": "회의 일정",
    "content": "좋은 일정이네요!"
  }
]
```

---

## 2. 댓글 생성
### **POST** `/comments/cl/{scheduleId}`
- **설명**: 특정 일정에 댓글을 추가
- **요청 파라미터**:
    - `scheduleId` (Path Variable, Long) : 댓글을 추가할 일정의 ID
- **요청 바디**:
```json
{
  "content": "댓글 내용"
}
```
- **응답 예시** (201 Created):
```json
"댓글 생성 됌"
```
- **인증 필요**: `LOGIN_ID` 세션 값 필요

---

## 3. 댓글 수정
### **PATCH** `/comments/cl/{commentId}`
- **설명**: 특정 댓글을 수정
- **요청 파라미터**:
    - `commentId` (Path Variable, Long) : 수정할 댓글의 ID
- **요청 바디**:
```json
{
  "content": "수정된 댓글 내용"
}
```
- **응답 예시** (200 OK):
```json
"수정 성공함"
```
- **인증 필요**: `LOGIN_ID` 세션 값 필요
- **제한 사항**: 댓글 작성자만 수정 가능

---

## 4. 댓글 삭제
### **DELETE** `/comments/cl/{commentId}`
- **설명**: 특정 댓글을 삭제
- **요청 파라미터**:
    - `commentId` (Path Variable, Long) : 삭제할 댓글의 ID
- **응답 예시** (200 OK):
```json
"삭제됌"
```
- **인증 필요**: `LOGIN_ID` 세션 값 필요
- **제한 사항**: 댓글 작성자만 삭제 가능

---

## 인증 및 권한
- `LOGIN_ID` 세션 값이 있어야 댓글 생성, 수정, 삭제 가능
- 본인이 작성한 댓글만 수정 및 삭제 가능
- 댓글 조회는 모든 사용자 가능

