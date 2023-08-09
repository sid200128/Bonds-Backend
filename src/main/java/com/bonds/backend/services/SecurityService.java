package com.bonds.backend.services;

import com.bonds.backend.models.Security;
import com.bonds.backend.models.Trade;
import com.bonds.backend.repository.SecurityRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class SecurityService {

    @Autowired
    private SecurityRepository securityRepository;

    public List<Security> getAllSecurities(){
        List<Security> securityList = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();
        securityRepository.findAll().forEach(securityList::add);
//        String jsonInString="";
//        try {
//            jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(securityList);
//        } catch (JsonProcessingException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return jsonInString;
        return securityList;

    }

    public Security getSecurityById(int id) {
       return securityRepository.findById(id).get();
    }

    public List<Security> getSecuritiesByDateRange(String startDateString, String endDateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        Date endDate = null;
        try {
            java.util.Date utilStartDate = dateFormat.parse(startDateString);
            startDate = new java.sql.Date(utilStartDate.getTime());
            java.util.Date utilEndDate = dateFormat.parse(endDateString);
            endDate = new java.sql.Date(utilEndDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return securityRepository.findByMaturityDateBetween(startDate, endDate);
    }

    public List<Trade> getAllTradesForSecurity(int id) {
        List<Trade> tradeList;
        tradeList=securityRepository.findById(id).get().getTradeList();
        return tradeList;
    }

    public void updateSecurity(Security security, int id) {
        securityRepository.save(security);
    }

    public void createSecurity(Security security) {
        securityRepository.save(security);
    }


    public void deleteSecurity(int id) {
        securityRepository.deleteById(id);
    }
}
