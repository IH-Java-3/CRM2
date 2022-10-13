package com.example.CRM2.repositories;

import com.example.CRM2.model.Leads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface LeadsRepository extends JpaRepository<Leads, Long> {
    //List<Leads> findBySalesRepId(String department);
    //select count(*) from Leads group by salesRepId
    @Query("select rp.name, count(l) from Leads l, SalesRep rp where rp.id = l.salesRepId group by l.salesRepId")
    List<ArrayList<String>> countBySalesRep();
}