package com.example.CRM2.model;

import javax.persistence.*;

@Entity
public class Opportunities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private OpportunitiesProduct product;
    private int quantity;
    @ManyToOne
    @JoinColumn(name = "decisionMaker_id")
    private Contacts decisionMaker;
    private OpportunitiesStatus status;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Accounts account;

    public Accounts getAccount() {
        return account;
    }

    public void setAccount(Accounts account) {
        this.account = account;
    }

    public Opportunities() {
    }

    public Opportunities(OpportunitiesProduct product, int quantity, Contacts decisionMaker, OpportunitiesStatus status, Accounts account) {
        this.product = product;
        this.quantity = quantity;
        this.decisionMaker = decisionMaker;
        this.status = status;
        this.account = account;
    }

    public Contacts getDecisionMaker() {
        return decisionMaker;
    }

    public void setDecisionMaker(Contacts decisionMaker) {
        this.decisionMaker = decisionMaker;
    }

    @Override
    public String toString() {
        return "\nId: " + id +
                "\nProduct: " + product +
                "\nQuantity: " + quantity +
                "\nDecisionMaker: " + decisionMaker +
                "\nStatus: " + status;
    }
}