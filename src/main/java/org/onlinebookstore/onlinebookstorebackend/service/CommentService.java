package org.onlinebookstore.onlinebookstorebackend.service;
import org.onlinebookstore.onlinebookstorebackend.dto.CommentDTO;
import org.onlinebookstore.onlinebookstorebackend.entity.Comment;

import java.util.List;

public interface CommentService {
    public List<Comment> getCommentsByBookId(Integer id);
    public boolean postComment(CommentDTO comment);
}
