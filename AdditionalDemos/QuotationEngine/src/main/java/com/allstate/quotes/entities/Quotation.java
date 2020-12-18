package com.allstate.quotes.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="quotations")
public class Quotation implements Serializable {


    public Quotation(String firstName, String lastName, Double quote) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.quote = quote;
    }

    public Quotation(){}

    @Column(name="id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;

    @Column(name="quote")
    private Double quote;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Double getQuote() {
        return quote;
    }

    public void setQuote(Double quote) {
        this.quote = quote;
    }
}
