package org.onlinebookstore.onlinebookstorebackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "author")
    private String author;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "cover")
    private String cover;

    @Column(name = "sales")
    private Integer sales;

    @Column(name = "stock")
    private Integer stock;

    @JsonIgnore
    @OneToMany(mappedBy = "book")
    private List<CartItem> CartItems;

}
