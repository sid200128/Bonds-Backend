package com.bonds.backend.controller;

import com.bonds.backend.models.Security;
import com.bonds.backend.models.Trade;
import com.bonds.backend.services.SecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class SecurityController {

    @Autowired
    private SecurityService securityService;

    // ------------------------------------------------------------------------------------
    //                              GET_ALL_SECURITIES
    //-------------------------------------------------------------------------------------

    @RequestMapping(method = RequestMethod.GET,value = "/security")
    public ResponseEntity<String> getAllSecurities(){
        return ResponseEntity.ok(securityService.getAllSecurities());
    }

    // ------------------------------------------------------------------------------------
    //                             GET_SECURITY_BY_ID
    //-------------------------------------------------------------------------------------

    @RequestMapping(method = RequestMethod.GET,value = "/security/{id}")
    public Security getSecurityById(@PathVariable int id){
        return securityService.getSecurityById(id);
    }

    // ------------------------------------------------------------------------------------
    //                             GET_SECURITY_BETWEEN_DATE_RANGE
    //-------------------------------------------------------------------------------------

    @RequestMapping(method = RequestMethod.GET,value = "/security/date/{startDate}/{endDate}")
    public List<Security> getSecuritiesByDateRange(@PathVariable String startDate,@PathVariable String endDate){
        return securityService.getSecuritiesByDateRange(startDate,endDate);
    }

    // ------------------------------------------------------------------------------------
    //                            GET_ALL_TRADES_FOR_SECURITY
    //-------------------------------------------------------------------------------------

    @RequestMapping(method = RequestMethod.GET,value = "/security/{id}/trades")
    public List<Trade> getAllTradesForSecurity(@PathVariable int id){
        return securityService.getAllTradesForSecurity(id);
    }

    // ------------------------------------------------------------------------------------
    //                            UPDATE_SECURITY
    //-------------------------------------------------------------------------------------

    @RequestMapping(method = RequestMethod.PUT,value = "/security/{id}/update")
    public void updateSecurity(@RequestBody Security security,@PathVariable int id){
        securityService.updateSecurity(security,id);
    }

    // ------------------------------------------------------------------------------------
    //                            CREATE_SECURITY
    //-------------------------------------------------------------------------------------

    @RequestMapping("/security/{id}/create")
    public void createSecurity(@RequestBody Security security){
        securityService.createSecurity(security);
    }

    // ------------------------------------------------------------------------------------
    //                            DELETE_SECURITY
    //-------------------------------------------------------------------------------------

    @RequestMapping("/security/{id}/delete")
    public void deleteSecurity(@PathVariable int id){
        securityService.deleteSecurity(id);
    }

}
