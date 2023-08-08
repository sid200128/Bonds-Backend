package com.bonds.backend.repository;

import com.bonds.backend.models.Trade;
import org.springframework.data.repository.CrudRepository;

public interface TradeRepository extends CrudRepository<Trade,Integer> {
}
