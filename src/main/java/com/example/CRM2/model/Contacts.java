package com.example.CRM2.model;

import javax.persistence.*;

@Entity
public class Contacts extends Leads{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Contacts() {
    }

    public Contacts(String name, String phoneNumber, String email, String companyName, Long salesRepId) {
        super(name, phoneNumber, email, companyName, salesRepId);
    }
}
