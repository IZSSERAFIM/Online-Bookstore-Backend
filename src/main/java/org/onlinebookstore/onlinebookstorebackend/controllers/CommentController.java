package org.onlinebookstore.onlinebookstorebackend.controllers;
import org.onlinebookstore.onlinebookstorebackend.dto.CommentDTO;
import org.onlinebookstore.onlinebookstorebackend.service.CommentService;
import org.onlinebookstore.onlinebookstorebackend.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    CommentService commentsService;

//    @CrossOrigin
    @RequestMapping("/comment")
    public ResponseEntity<List<Comment>> getCommentsByBookIdHandler(Integer id) {
        return new ResponseEntity<>(commentsService.getCommentsByBookId(id), HttpStatus.ACCEPTED);
    }

//    @CrossOrigin
    @RequestMapping(value = "/comment/post", method = RequestMethod.POST)
    public boolean postComment(@RequestBody CommentDTO comment) {
        return commentsService.postComment(comment);
    }
}

