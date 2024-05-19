package org.onlinebookstore.onlinebookstorebackend.dto;
import org.onlinebookstore.onlinebookstorebackend.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDTO {
    private Integer bookId;
    private String text;
    private String username;
}

