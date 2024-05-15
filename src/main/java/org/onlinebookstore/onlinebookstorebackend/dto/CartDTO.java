package org.onlinebookstore.onlinebookstorebackend.dto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CartDTO {
    String date;
    String name;
    Integer bookId;
}
