-- user
-- userRepository.findByLoginId(String loginId)
SELECT *
FROM user
WHERE login_id = ?;

-- userRepository.findByEmail(String email)
SELECT *
FROM user
WHERE email = ?;

-- userRepository.save(User user)
-- 데이터 생성
INSERT INTO user (login_id, email, password, user_name, create_date)
VALUES (?, ?, ?, ?, ?);

-- 데이터 수정
UPDATE user
SET password = ?
WHERE login_id = ?;

-- userRepository.delete(User user)
DELETE FROM user
WHERE login_id = ?;

-- userRepository.findAll()
SELECT *
FROM user;

-- userRepository.findAll(Pageable pageable)
SELECT *
FROM user
ORDER BY id
LIMIT 10 OFFSET 10;

--  userRepository.findByLoginId(loginId).orElseThrow(...)
SELECT *
FROM user
WHERE login_id = ?;

-- schedule
-- scheduleRepository.save(new Schedule(...))
INSERT INTO schedule (user_name, title, content, user_id, create_date, update_date)
VALUES (?, ?, ?, ?, now(), now())

-- scheduleRepository.findById(scheduleId)
SELECT *
FROM schedule
WHERE id = ?;

-- scheduleRepository.findAll()
SELECT *
FROM schedule;

-- scheduleRepository.findAll(Pageable pageable)
SELECT *
FROM schedule
LIMIT 10 OFFSET 10;

-- userRepository.findByLoginId(loginId)
SELECT *
FROM user
WHERE login_id = ?;

-- scheduleRepository.delete(schedule)
DELETE FROM schedule
WHERE id = ?;

-- scheduleRepository.save(schedule) 일정 수정
UPDATE schedule
SET title = ?, content = ?, update_date = now()
WHERE id = ?;

-- scheduleRepository.findById(scheduleId).get().getUser().getLoginId()
SELECT u.login_id
FROM schedule s JOIN user u ON s.user_id = u.id
WHERE s.id = ?;

-- Comment
-- commentRepository.save(new Comment(...))
INSERT INTO comment (content, user_id, schedule_id, create_date, update_date)
VALUES (?, ?, ?, now(), now());

-- userRepository.findByLoginId(loginId)
SELECT *
FROM user
WHERE login_id = ?;

-- scheduleRepository.findById(scheduleId)
SELECT *
FROM schedule
WHERE id = ?;

-- commentRepository.findById(commentId)
SELECT *
FROM comment
WHERE id = ?;

-- commentRepository.save(comment) 댓글 수정
UPDATE comment
SET content = ?, update_date = NOW()
WHERE id = ?;

--  commentRepository.deleteById(commentId)
DELETE FROM comment
WHERE id = ?;

-- commentRepository.findAll()
SELECT *
FROM comment;

-- commentRepository.findAll().stream().filter(comment -> comment.getSchedule().getId().equals(scheduleId))
SELECT *
FROM comment
WHERE schedule_id = ?;