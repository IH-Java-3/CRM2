package com.example.CRM2.repositories;

import com.example.CRM2.model.Opportunities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpportunitiesRepository extends JpaRepository<Opportunities, Long> {
}
