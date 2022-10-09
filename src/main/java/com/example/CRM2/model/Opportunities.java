package com.example.CRM2.model;

import javax.persistence.*;

@Entity
public class Opportunities {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Opportunities_product product;
    private int quantity;
    private Long decisionMaker; //este seria el contact id
    private Opportunities_Status status;
    /*
    @OneToOne
    @JoinColumn(name = "decision_maker_id")
    private Leads decisionMaker;

    @ManyToOne
    @JoinColumn(name = "sales_rep_id")
    private SalesRep salesRep;
*/

    public Opportunities() {
    }

    public Opportunities(Opportunities_product product, int quantity, Long decisionMaker, Opportunities_Status status) {
        this.product = product;
        this.quantity = quantity;
        this.decisionMaker = decisionMaker;
        this.status = status;
    }
}
