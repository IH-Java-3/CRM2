package com.example.CRM2.repositories;

import com.example.CRM2.model.Opportunities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface OpportunitiesRepository extends JpaRepository<Opportunities, Long> {
    @Query("select rp.name, count(l) from Opportunities l, SalesRep rp, Contacts c where rp.id = c.salesRepId group by c.salesRepId")
    List<ArrayList<String>> countBySalesRep();
    @Query("select rp.name, count(l) from Opportunities l, SalesRep rp, Contacts c where rp.id = c.salesRepId AND l.status = 1")
    List<ArrayList<String>> countCLOSEDWONBySalesRep();
    @Query("select rp.name, count(l) from Opportunities l, SalesRep rp, Contacts c where rp.id = c.salesRepId AND l.status = 2")
    List<ArrayList<String>> countCLOSEDLOSTBySalesRep();
    @Query("select rp.name, count(l) from Opportunities l, SalesRep rp, Contacts c where rp.id = c.salesRepId AND l.status = 0")
    List<ArrayList<String>> countOPENBySalesRep();

    @Query("select product, count(l) from Opportunities l group by product")
    List<ArrayList<String>> countByProduct();
    @Query("select product, count(l) from Opportunities l where l.status = 1 group by product")
    List<ArrayList<String>> countCLOSEDWONByProduct();
    @Query("select product, count(l) from Opportunities l where l.status = 2 group by product")
    List<ArrayList<String>> countCLOSEDLOSTByProduct();
    @Query("select product, count(l) from Opportunities l where l.status = 0 group by product")
    List<ArrayList<String>> countOPENByProduct();

    @Query("select ac.country, count(l) from Opportunities l, Accounts ac where ac.id = l.account group by ac.country")
    List<ArrayList<String>> countByCountry();
    @Query("select ac.country, count(l) from Opportunities l, Accounts ac where ac.id = l.account and l.status = 1 group by ac.country")
    List<ArrayList<String>> countCLOSEDWONByCountry();
    @Query("select ac.country, count(l) from Opportunities l, Accounts ac where ac.id = l.account and l.status = 2 group by ac.country")
    List<ArrayList<String>> countCLOSEDLOSTByCountry();
    @Query("select ac.country, count(l) from Opportunities l, Accounts ac where ac.id = l.account and l.status = 0 group by ac.country")
    List<ArrayList<String>> countOPENByCountry();

    @Query("select ac.country, count(l) from Opportunities l, Accounts ac where ac.id = l.account group by ac.city")
    List<ArrayList<String>> countByCity();
    @Query("select ac.country, count(l) from Opportunities l, Accounts ac where ac.id = l.account and l.status = 1 group by ac.city")
    List<ArrayList<String>> countCLOSEDWONByCity();
    @Query("select ac.country, count(l) from Opportunities l, Accounts ac where ac.id = l.account and l.status = 2 group by ac.city")
    List<ArrayList<String>> countCLOSEDLOSTByCity();
   @Query("select ac.country, count(l) from Opportunities l, Accounts ac where ac.id = l.account and l.status = 0 group by ac.city")
    List<ArrayList<String>> countOPENByCity();

    @Query("select ac.country, count(l) from Opportunities l, Accounts ac where ac.id = l.account group by ac.accountIndustry")
    List<ArrayList<String>> countByIndustry();
    @Query("select ac.country, count(l) from Opportunities l, Accounts ac where ac.id = l.account and l.status = 1 group by ac.accountIndustry")
    List<ArrayList<String>> countCLOSEDWONByIndustry();
    @Query("select ac.country, count(l) from Opportunities l, Accounts ac where ac.id = l.account and l.status = 2 group by ac.accountIndustry")
    List<ArrayList<String>> countCLOSEDLOSTByIndustry();
    @Query("select ac.country, count(l) from Opportunities l, Accounts ac where ac.id = l.account and l.status = 0 group by ac.accountIndustry")
    List<ArrayList<String>> countOPENByIndustry();

    @Query("select avg(quantity) from Opportunities")
    List<ArrayList<String>> productsMean();

    @Query(value = "with V as (select *, count(*) over () as a, row_number() over (order by product) as RN from Opportunities) select avg(product) from V where RN in (floor((a+1)/2), floor((a+2)/2))", nativeQuery = true)
    List<ArrayList<String>> productsMedian();

    @Query(value = "SELECT account_id, count(product) FROM opportunities group by account_id order by product ASC limit 1", nativeQuery = true)
    List<ArrayList<String>> productsMax();
    @Query(value = "SELECT account_id, count(product) FROM opportunities group by account_id order by product DESC limit 1", nativeQuery = true)
    List<ArrayList<String>> productsMin();


    @Query("select avg(account) from Opportunities")
    List<ArrayList<String>> opportunitiesMean();

    @Query(value = "with V as (select *, count(*) over () as a, row_number() over (order by account_id) as RN from Opportunities) select avg(account_id) from V where RN in (floor((a+1)/2), floor((a+2)/2))", nativeQuery = true)
    List<ArrayList<String>> opportunitiesMedian();

    @Query(value = "SELECT account_id, count(account_id) FROM opportunities group by account_id order by account_id ASC limit 1", nativeQuery = true)
    List<ArrayList<String>> opportunitiesMax();

    @Query(value = "SELECT account_id, count(account_id) FROM opportunities group by account_id order by account_id DESC limit 1", nativeQuery = true)
    List<ArrayList<String>> opportunitiesMin();
}

//SELECT account_id, count(account_id) FROM `opportunities` group by account_id order by account_id ASC limit 1
//select rp.name, count(*) from Opportunities l, Sales_Rep rp, contacts c where rp.id = c.sales_rep_id_id  group by c.sales_rep_id_id
//select rp.name, count(*) from Opportunities l, Sales_Rep rp, contacts c where rp.id = c.sales_rep_id_id  AND l.status = 1;
//@Query("select rp.name, count(l) from Opportunities l, SalesRep rp where rp.id = l.decisionMaker group by l.decisionMaker")
//select product, count(*) from Opportunities group by product
//select count(*), country from opportunities l, accounts ac where ac.id = l.account_id GROUP by country