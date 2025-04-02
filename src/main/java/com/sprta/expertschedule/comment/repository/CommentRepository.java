package com.sprta.expertschedule.comment.repository;

import com.sprta.expertschedule.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
