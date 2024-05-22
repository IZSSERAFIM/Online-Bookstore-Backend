package org.onlinebookstore.onlinebookstorebackend.dao;

import org.onlinebookstore.onlinebookstorebackend.entity.Comment;
import java.util.List;

public interface CommentDao {
    List<Comment> getCommentsByBookId(Integer bookId);
    void addComment(Comment comment);
}