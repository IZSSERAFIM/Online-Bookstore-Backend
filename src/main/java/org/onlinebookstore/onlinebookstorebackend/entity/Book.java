package org.onlinebookstore.onlinebookstorebackend.entity;

public class Book {

    private Integer id;
    private String title;
    private String author;
    private String description;
    private Integer price;
    private String cover;
    private Integer sales;
    private Integer stock;

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Book(Integer id, String title, String author, String description, Integer price, String cover, Integer sales, Integer stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.price = price;
        this.cover = cover;
        this.sales = sales;
        this.stock = stock;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return String.format(
                "Book[id=%d, title='%s', author='%s', description='%s', price=%d, cover='%s', sales=%d]",
                id, title, author, description, price, cover, sales);
    }
}
