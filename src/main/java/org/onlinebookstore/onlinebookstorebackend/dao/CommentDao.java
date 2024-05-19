package org.onlinebookstore.onlinebookstorebackend.dao;
import org.onlinebookstore.onlinebookstorebackend.entity.Comment;
import org.onlinebookstore.onlinebookstorebackend.repository.CommentRepository;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentDao {
    @Resource
    CommentRepository commentRepo;

    public List<Comment> getCommentsByBookId(Integer bookId){
        List<Comment> commentList = commentRepo.findByBookId(bookId);
        return commentList;
    }

    public void addComment(Comment comment){
        commentRepo.save(comment);
    }
}
