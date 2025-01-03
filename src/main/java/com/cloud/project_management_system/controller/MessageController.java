package com.cloud.project_management_system.controller;


import com.cloud.project_management_system.dto.MessageRequestDTO;
import com.cloud.project_management_system.exceptions.ProjectException;
import com.cloud.project_management_system.model.Chat;
import com.cloud.project_management_system.model.Message;
import com.cloud.project_management_system.model.User;
import com.cloud.project_management_system.response.MessageResponse;
import com.cloud.project_management_system.service.impl.ChatServiceImpl;
import com.cloud.project_management_system.service.impl.ProjectServiceImpl;
import com.cloud.project_management_system.service.impl.UserServiceImpl;
import com.cloud.project_management_system.service.interfaces.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/messages")
@RequiredArgsConstructor
public class MessageController {

  private final UserServiceImpl userService;
  private final MessageService messageService;
  private final ProjectServiceImpl projectService;

  @GetMapping
  public ResponseEntity<List<Message>> getAllMessages(){
    List<Message> messages = messageService.getAllMessages();
    return ResponseEntity.ok(messages);
  }

  @GetMapping("/{projectId}")
  public ResponseEntity<List<Message>> getMessagesByProjectId(@PathVariable Long projectId){

    List<Message> messages = messageService.getMessagesByProjectId(projectId);
    return  ResponseEntity.ok(messages);
  }

  @PostMapping("/send")
  public ResponseEntity<MessageResponse> sendMessage(@RequestBody MessageRequestDTO req,
                                                     @RequestHeader("Authorization") String jwt){

    User user = userService.findUserProfileByJwt(jwt);

    Chat chat = projectService.getChatByProjectId(req.getProjectId());

    if(chat == null) throw new ProjectException("Chat not found");

    messageService.sendMessage(user.getId(),req.getProjectId(), req.getMessage());
    MessageResponse res = new MessageResponse("Message sent");

    return ResponseEntity.status(HttpStatus.CREATED).body(res);
  }

  @PutMapping("/edit/{id}")
  public ResponseEntity<MessageResponse> editMessage(@RequestBody String msg,
                                                     @PathVariable Long id,
                                                     @RequestHeader("Authorization") String jwt){
    User user = userService.findUserProfileByJwt(jwt);
    MessageResponse res = new MessageResponse("Message updated");
    messageService.editMessage(user.getId(),id,msg);
    return ResponseEntity.ok(res);
  }

  @DeleteMapping("/delete/{id}")
  public ResponseEntity<MessageResponse> deleteMessage(@PathVariable Long id,
                                                       @RequestHeader("Authorizaation") String jwt){
    User user = userService.findUserProfileByJwt(jwt);
   messageService.deleteMessage(id, user.getId());
   MessageResponse res = new MessageResponse("Message deleted");
   return ResponseEntity.ok(res);
  }



}
