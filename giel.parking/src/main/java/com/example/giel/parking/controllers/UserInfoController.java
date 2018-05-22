package com.example.giel.parking.controllers;


import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;


import com.example.giel.parking.models.UserInfo;
import com.example.giel.parking.services.interfaces.IUserInfoService;

import javax.validation.constraints.Max.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserInfoController {

  @Autowired
  private IUserInfoService userInfoService;


  @PostMapping()
  public Long createNewEmptyUser() {
    return userInfoService.createNewEmptyUser();
  }

  @GetMapping()
  public ResponseEntity getAllUserInfos() {
    return ResponseEntity.ok(userInfoService.getAllUserInfos());
  }

}
