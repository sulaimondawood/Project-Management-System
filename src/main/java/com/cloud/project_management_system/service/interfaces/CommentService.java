package com.cloud.project_management_system.service.interfaces;

import com.cloud.project_management_system.model.Comment;

import java.util.List;

public interface CommentService {
  List<Comment> getAllComments();

  List<Comment> getCommentByIssueId(Long issueId);

  void createComment(String comment, Long userId, Long issueId);

  void deleteComment(Long userId, Long commentId);
}
