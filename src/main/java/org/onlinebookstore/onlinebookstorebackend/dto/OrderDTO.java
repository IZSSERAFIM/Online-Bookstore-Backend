package org.onlinebookstore.onlinebookstorebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderDTO {
    String date;
    String name;
    List<Integer> bookIdList;
    List<Integer> bookNumList;
}
