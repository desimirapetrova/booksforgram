package com.example.booksforgram.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "promo_books")
public class PromoBook extends BaseEntity{

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @Lob
    private String description;
    @Column(nullable = false)
    private Integer price;
    private Integer quantity=1;
    @ManyToOne()
    private Condition condition;
    @ManyToOne
    private Category category;
    @Column(nullable = false)
    private String author;
    @ManyToOne
    private User owner;
    //    @Column( columnDefinition = "TEXT")
    @Lob
    @Column(nullable = false)
    private String imageUrl;

//    @OneToOne
//    private Picture pictures;

    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Comment> comments;

    public PromoBook() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
