package com.bonds.backend.services;

import com.bonds.backend.models.Security;
import com.bonds.backend.models.Trade;
import com.bonds.backend.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TradeService {


    @Autowired
    private TradeRepository tradeRepository;

    public List<Trade> getAllTrades() {
        List<Trade> tradeList=new ArrayList<>();
        tradeRepository.findAll().forEach(tradeList::add);
        return tradeList;
    }

    public Trade getTradeById(int id) {
        return tradeRepository.findById(id).get();
    }

    public Security getSecurityByTrade(int id) {
        return tradeRepository.findById(id).get().getSecurity();
    }

    public void updateTrade(Trade trade,int id) {

        Security currentSecurity = getSecurityByTrade(id);
        if(currentSecurity!=null){
            currentSecurity.getTradeList().clear();
        }
        tradeRepository.save(trade);
    }

    public void createTrade(Trade trade) {
        tradeRepository.save(trade);
    }
}
