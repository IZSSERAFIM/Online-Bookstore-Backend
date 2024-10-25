package org.onlinebookstore.onlinebookstorebackend.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.relational.core.sql.In;

@Data
@AllArgsConstructor
public class UserDTO {
    private String name;
    private String password;
    private Integer id;
    private String email;
    private String address;
    private String phone;
    private String description;

    public UserDTO(String name) {
        this.name = name;
    }
}
