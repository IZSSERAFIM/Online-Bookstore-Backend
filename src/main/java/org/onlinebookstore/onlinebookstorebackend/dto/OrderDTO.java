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

    // 添加无参构造函数
    public OrderDTO() {
    }

    public OrderDTO(String date_prop, String name_prop, String bookIdList_prop, String bookNumList_prop) {
        this.date = date_prop;
        this.name = name_prop;
        this.bookIdList = List.of(bookIdList_prop.replaceAll("\\[|\\]", "").split(","))
                .stream()
                .map(String::trim)  // 添加 trim()
                .map(Integer::parseInt)
                .toList();
        this.bookNumList = List.of(bookNumList_prop.replaceAll("\\[|\\]", "").split(","))
                .stream()
                .map(String::trim)  // 添加 trim()
                .map(Integer::parseInt)
                .toList();
    }
}
