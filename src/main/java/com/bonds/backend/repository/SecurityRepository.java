package com.bonds.backend.repository;

import com.bonds.backend.models.Security;
import org.springframework.data.repository.CrudRepository;

import java.sql.Date;
import java.util.List;

public interface SecurityRepository extends CrudRepository<Security,Integer> {
    List<Security> findByMaturityDateBetween(Date startDate,Date endDate);
}
