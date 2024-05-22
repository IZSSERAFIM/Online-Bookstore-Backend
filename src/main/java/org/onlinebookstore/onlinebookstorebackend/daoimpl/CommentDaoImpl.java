package org.onlinebookstore.onlinebookstorebackend.daoimpl;

import org.onlinebookstore.onlinebookstorebackend.dao.CommentDao;
import org.onlinebookstore.onlinebookstorebackend.entity.Comment;
import org.onlinebookstore.onlinebookstorebackend.repository.CommentRepository;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;

import java.util.List;

@Service
public class CommentDaoImpl implements CommentDao {
    @Resource
    private CommentRepository commentRepo;

    @Override
    public List<Comment> getCommentsByBookId(Integer bookId){
        List<Comment> commentList = commentRepo.findByBookId(bookId);
        return commentList;
    }

    @Override
    public void addComment(Comment comment){
        commentRepo.save(comment);
    }
}