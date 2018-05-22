package com.example.giel.parking.services.interfaces;

public interface IBalanceService {

  Double getCurrentCustomerBalanceByUuid(String uuid);

  Double getPreviousCustomerBalanceById(Long userInfoId);

}
