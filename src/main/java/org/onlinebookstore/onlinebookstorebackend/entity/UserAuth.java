package org.onlinebookstore.onlinebookstorebackend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "userauth")
public class UserAuth {
    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "password")
    private String password;
}

