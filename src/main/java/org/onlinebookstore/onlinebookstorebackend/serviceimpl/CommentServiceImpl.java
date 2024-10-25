package org.onlinebookstore.onlinebookstorebackend.serviceimpl;

import org.onlinebookstore.onlinebookstorebackend.service.CommentService;
import org.onlinebookstore.onlinebookstorebackend.dto.CommentDTO;
import org.onlinebookstore.onlinebookstorebackend.entity.Comment;
import org.onlinebookstore.onlinebookstorebackend.dao.CommentDao;
import org.onlinebookstore.onlinebookstorebackend.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentdao;
    @Autowired
    UserDao userdao;

    @Override
    @Cacheable(value = "comments")
    public List<Comment> getCommentsByBookId(Integer id) {
        return commentdao.getCommentsByBookId(id);
    }

    @Override
    @CachePut(value = "comment", key = "#commentDto.username")
    public boolean postComment(CommentDTO commentDto) {
        System.out.println(commentDto.getUsername());
        Comment comment = new Comment(null,
                commentDto.getBookId(),
                commentDto.getText(),
                0,
                userdao.getByName(commentDto.getUsername()));
        commentdao.addComment(comment);
        return true;
    }
}
