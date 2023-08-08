package com.bonds.backend.repository;

import com.bonds.backend.models.Counterparty;
import org.springframework.data.repository.CrudRepository;

public interface CounterpartyRepository extends CrudRepository<Counterparty,Integer> {
}
