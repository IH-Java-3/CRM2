package com.example.CRM2.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Accounts {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Account_Industry industry;
    private int employeeCount;
    private String city;
    private String country;
    /*
    @OneToMany
    @JoinColumn(name = "contactList_id")
    private List<Leads> contactList = new ArrayList<>();  //aquí va una lista con la info de leads
    @OneToMany
    @JoinColumn(name = "opportunityList_id")
    private List<Opportunities> opportunityList = new ArrayList<>();
     */

    public Accounts() {
    }


}
