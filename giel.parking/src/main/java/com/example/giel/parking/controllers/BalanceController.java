package com.example.giel.parking.controllers;

import com.example.giel.parking.services.interfaces.IBalanceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/qr-service/balance")
public class BalanceController {

  @Autowired
  IBalanceService balanceService;

  @CrossOrigin(origins = "http://localhost:3000")
  @GetMapping(value = "/current/{useruuId}")
  public Double getCurrentCustomerBalanceByUuid(@PathVariable("useruuId") String uuid) {
    return balanceService.getCurrentCustomerBalanceByUuid(uuid);
  }
}
