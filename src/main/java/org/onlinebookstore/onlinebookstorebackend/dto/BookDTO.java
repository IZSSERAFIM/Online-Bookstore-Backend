package org.onlinebookstore.onlinebookstorebackend.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookDTO {
    private String title;
    private String author;
    private String description;
    private Integer price;
    private String cover;
    private Integer id;
    private Integer stock;
}
