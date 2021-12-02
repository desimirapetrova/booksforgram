package com.example.booksforgram.model.binding;

import com.example.booksforgram.model.entity.Book;
import com.example.booksforgram.model.entity.User;

import javax.validation.constraints.*;
import java.util.List;

public class OrderBindingModel {
    @Size(min = 3,message = "Името трябва да съдържа минимум 3 символа")
    private String firstName;
    @Size(min = 3,message = "Фамилията трябва да съдържа минимум 3 символа")
    private String lastName;
    @NotEmpty(message = "Моля, въведете имейл")
    @Email(message = "Моля, въведете валиден имейл")
    private String email;
    @NotEmpty(message = "Моля, въведете адрес")
    private String address;
    @NotNull(message = "Моля, въведете номер")
    @Min(value = 10,message = "Моля, въведете валиден номер")
    private Integer phoneNumber;
    @NotEmpty(message = "Моля, въведете град")
    private String city;
    @NotNull(message = "Моле, въведете код")
    @Min(value = 4,message = "Моля,въведете валиден код")
    private Integer zip;
    @NotEmpty(message = "Моля, въведете име на картата")
    private String cardName;
    @NotNull(message = "Моля, въведете номер на картата")
    private Integer cardNumber;
    @NotEmpty(message = "Моля, въведете месец")
    private String ExpMonth;
    @NotEmpty(message = "Моля, въведете година")
    private String ExpYear;
    @NotNull(message = "Моля, въведете cvv")
    @Min(value = 3,message = "CVV трябва да съдържа 3цифри")
    private Integer CVV;
    private User buyer;
    private List<Book> bookList;

    public OrderBindingModel() {
    }



    public Integer getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(Integer phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getZip() {
        return zip;
    }

    public void setZip(Integer zip) {
        this.zip = zip;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpMonth() {
        return ExpMonth;
    }

    public void setExpMonth(String expMonth) {
        ExpMonth = expMonth;
    }

    public String getExpYear() {
        return ExpYear;
    }

    public void setExpYear(String expYear) {
        ExpYear = expYear;
    }

    public Integer getCVV() {
        return CVV;
    }

    public void setCVV(Integer CVV) {
        this.CVV = CVV;
    }
}
