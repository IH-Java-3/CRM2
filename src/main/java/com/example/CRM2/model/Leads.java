package com.example.CRM2.model;

import javax.persistence.*;

@Entity
public class Leads {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String phoneNumber;
    private String email;
    private String companyName;
    private long salesRepId;

    public Leads() {
    }

    public Leads(String name, String phoneNumber, String email, String companyName, Long salesRepId) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.companyName = companyName;
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
