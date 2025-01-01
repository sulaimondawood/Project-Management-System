package com.cloud.project_management_system.controller;

import com.cloud.project_management_system.dto.CommentRequestDTO;

import com.cloud.project_management_system.model.Comment;
import com.cloud.project_management_system.model.User;
import com.cloud.project_management_system.response.MessageResponse;
import com.cloud.project_management_system.service.impl.UserServiceImpl;
import com.cloud.project_management_system.service.interfaces.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/comments")
@RequiredArgsConstructor
public class CommentController {
  private final CommentService commentService;
  private final UserServiceImpl userService;

  @PostMapping
  public ResponseEntity<MessageResponse> createComment(@RequestBody CommentRequestDTO req,
                                               @RequestHeader("Authorization") String jwt){
    User user = userService.findUserProfileByJwt(jwt);
    commentService.createComment(req.getComment(),user.getId(),req.getIssueId());
    MessageResponse res = new MessageResponse("Comment submitted");
    return ResponseEntity.status(HttpStatus.CREATED).body(res);
  }

  @GetMapping
  public ResponseEntity<List<Comment>> getAllComment(){
    return ResponseEntity.ok(commentService.getAllComments());
  }

  @GetMapping("/{issueId}")
  public ResponseEntity<List<Comment>> getCommentByIssueId(@PathVariable Long issueId){
    List<Comment> comment = commentService.getCommentByIssueId(issueId);

    return ResponseEntity.ok(comment);
  }

  @DeleteMapping("/{commentId}")
  public ResponseEntity<MessageResponse> deleteComment(@PathVariable Long commentId,
                                                       @RequestHeader("Authorization") String jwt){

    User user = userService.findUserProfileByJwt(jwt);
    MessageResponse res = new MessageResponse("Comment deleted");
    commentService.deleteComment(user.getId(), commentId);
    return ResponseEntity.ok(res);
  }

}
