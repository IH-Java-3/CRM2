package com.example.CRM2.model;

import javax.persistence.*;

@Entity
public class Contacts extends Leads{
    @ManyToOne
    Accounts account;

    public Accounts getAccount() {
        return account;
    }

    public void setAccount(Accounts account) {
        this.account = account;
    }

    public Contacts() {
    }

    public Contacts(String name, String phoneNumber, String email, String companyName, SalesRep salesRepId, Accounts account) {
        super(name, phoneNumber, email, companyName, salesRepId);
        this.account = account;
    }
}
