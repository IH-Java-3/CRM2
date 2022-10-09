package com.example.CRM2.repositories;

import com.example.CRM2.model.Contacts;
import com.example.CRM2.model.Leads;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactsRepository extends JpaRepository<Contacts, Long> {
}