package com.cloud.project_management_system.service.impl;

import com.cloud.project_management_system.model.Comment;
import com.cloud.project_management_system.model.Issue;
import com.cloud.project_management_system.model.User;
import com.cloud.project_management_system.repository.CommentRepository;
import com.cloud.project_management_system.service.interfaces.CommentService;
import com.cloud.project_management_system.service.interfaces.IssueService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {
  private final CommentRepository commentRepository;
  private final IssueService issueService;
  private final UserServiceImpl userService;


  @Override
  public List<Comment> getAllComments(){
    return commentRepository.findAll(Sort.by(Sort.Direction.ASC));
  }

  @Override
  public void createComment(String comment, Long userId, Long issueId) {
    Comment newComment = new Comment();
    User user = userService.findUserById(userId);
    Issue issue = issueService.getIssueById(issueId);

    newComment.setComment(comment);
    newComment.setCreatedAt(LocalDateTime.now());
    newComment.setUser(user);
    newComment.setIssue(issue);

    commentRepository.save(newComment);
    issue.getComments().add(newComment);
  }

  @Override
  public void deleteComment(Long userId, Long commentId) {

  }
}
