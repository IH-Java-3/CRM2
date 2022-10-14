package com.example.CRM2.repositories;

import com.example.CRM2.model.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface AccountsRepository extends JpaRepository<Accounts, Long> {

    @Query("select avg(employeeCount) from Accounts")
    List<ArrayList<String>> employeeMean();
    /*
    @Query("select *")
    List<ArrayList<String>> employeeMedian();
    */
    @Query("select max(employeeCount) from Accounts")
    List<ArrayList<String>> employeeMax();
    @Query("select min(employeeCount) from Accounts")
    List<ArrayList<String>> employeeMin();
    @Query(value = "with V as (select *, count(*) over () as a, row_number() over (order by employee_count) as RN from Accounts) select avg(employee_count) from V where RN in (floor((a+1)/2), floor((a+2)/2))", nativeQuery = true)
    List<ArrayList<String>> employeeMedian();

    //with V as (select *, count(*) over () as a, row_number() over (order by employeeCount) as RN from Accounts) select avg(employeeCount) from V where RN in (floor((a+1)/2), floor((a+2)/2));
}