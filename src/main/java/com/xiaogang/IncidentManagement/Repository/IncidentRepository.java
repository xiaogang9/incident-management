package com.xiaogang.IncidentManagement.Repository;

import com.xiaogang.IncidentManagement.Entity.Incident;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
    boolean existsByTitle(String title);
}
