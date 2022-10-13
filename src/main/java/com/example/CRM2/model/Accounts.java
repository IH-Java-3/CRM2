package com.example.CRM2.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private AccountIndustry accountIndustry;
    private int employeeCount;
    private String city;
    private String country;

    @OneToMany(mappedBy = "account")
    private List<Contacts> contactList = new ArrayList<>();  //aquí va una lista con la info de contactos

    @OneToMany(mappedBy = "account")
    private List<Opportunities> opportunityList = new ArrayList<>(); //lista de oportunidades

    public Accounts() {
    }

    //public Accounts(AccountIndustry accountIndustry, int employeeCount, String city, String country, List<Contacts> contactList, List<Opportunities> opportunityList) {
    public Accounts(AccountIndustry accountIndustry, int employeeCount, String city, String country) {
        this.accountIndustry = accountIndustry;
        this.employeeCount = employeeCount;
        this.city = city;
        this.country = country;
        //this.contactList = contactList;
        //this.opportunityList = opportunityList;
    }
}