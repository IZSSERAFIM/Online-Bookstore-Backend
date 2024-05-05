package org.onlinebookstore.onlinebookstorebackend.entity;

import org.onlinebookstore.onlinebookstorebackend.entity.Book;
import java.util.List;

public class Order {
    private Integer id;
    private String receiver;
    private String address;
    private String tel;
    private String createdAt;
    private List<Item> items;

    public Order(Integer id, String receiver, String address, String tel, String createdAt, List<Item> items) {
        this.id = id;
        this.receiver = receiver;
        this.address = address;
        this.tel = tel;
        this.createdAt = createdAt;
        this.items = items;
    }

    // getters and setters

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public static class Item {
        private Integer id;
        private Book book;
        private Integer number;

        public Item(Integer id, Book book, Integer number) {
            this.id = id;
            this.book = book;
            this.number = number;
        }

        // getters and setters

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Book getBook() {
            return book;
        }

        public void setBook(Book book) {
            this.book = book;
        }

        public Integer getNumber() {
            return number;
        }

        public void setNumber(Integer number) {
            this.number = number;
        }


    }

}