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
}