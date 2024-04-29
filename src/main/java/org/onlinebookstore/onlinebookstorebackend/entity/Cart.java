package org.onlinebookstore.onlinebookstorebackend.entity;

public class Cart {
    private Integer id;
    private String name;
    private Integer price;
    private String cover;
    private Integer number;

    public Cart(Integer id, String name, Integer price, String cover, Integer number) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.cover = cover;
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return String.format("Cart{id=%d, name='%s', price=%d, cover='%s', number=%d}", id, name, price, cover, number);
    }
}
