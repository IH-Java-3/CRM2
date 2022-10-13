package com.example.CRM2.model;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Leads {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String companyName;
    @ManyToOne
    private SalesRep salesRepId;

    public Leads() {
    }

    public Leads(String name, String phoneNumber, String email, String companyName, SalesRep salesRepId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
        this.salesRepId = salesRepId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public SalesRep getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(SalesRep salesRepId) {
        this.salesRepId = salesRepId;
    }

    @Override
    public String toString() {
        return "\nId: " + id +
                "\nName: " + name +
                "\nPhoneNumber: " + phoneNumber +
                "\nE-mail: " + email +
                "\nCompany Name: " + companyName +
                "\nSalesRepId: " + salesRepId;
    }
}
