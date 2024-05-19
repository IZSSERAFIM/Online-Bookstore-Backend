package org.onlinebookstore.onlinebookstorebackend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "book_id")
    private Integer bookId;

    @Column(name = "text")
    private String text;

    @Column(name = "likes")
    private Integer likes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}