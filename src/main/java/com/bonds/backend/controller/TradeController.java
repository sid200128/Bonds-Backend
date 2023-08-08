package com.bonds.backend.controller;


import com.bonds.backend.models.Security;
import com.bonds.backend.models.Trade;
import com.bonds.backend.services.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TradeController {

    @Autowired
    private TradeService tradeService;

    // -----------------------------------------------------------------------------
    //                                   GET_ALL_TRADES
    // -----------------------------------------------------------------------------


    @RequestMapping(method = RequestMethod.GET,value = "/alltrades")
    public List<Trade> getAllTrades(){
        return tradeService.getAllTrades();
    }

    // -----------------------------------------------------------------------------
    //                                   GET_TRADE_BY_ID
    // -----------------------------------------------------------------------------

    @RequestMapping(method=RequestMethod.GET,value="/trade/{id}")
    public Trade getTradeById(@PathVariable int id){
        return tradeService.getTradeById(id);
    }


    // -----------------------------------------------------------------------------
    //                                   RETRIEVE_SECURITY_BY_TRADE
    // -----------------------------------------------------------------------------

    @RequestMapping(method = RequestMethod.GET,value = "/findSecurityByTrade/{id}")
    public Security getSecurityByTrade(@PathVariable int id){
        return tradeService.getSecurityByTrade(id);
    }

    // -----------------------------------------------------------------------------
    //                                   UPDATE_TRADE
    // -----------------------------------------------------------------------------

    @RequestMapping(method = RequestMethod.PUT,value = "/trade/update/{id}")
//    @PutMapping("/trade/update/{id}")
    public void updateTrade(@RequestBody Trade trade, @PathVariable int id){
        tradeService.updateTrade(trade,id);
    }


    // -----------------------------------------------------------------------------
    //                                  CREATE_TRADE
    // -----------------------------------------------------------------------------

    @RequestMapping(method = RequestMethod.POST,value = "/trade/create")
    public void createTrade(@RequestBody Trade trade){
        tradeService.createTrade(trade);
    }
}
