package com.example.giel.parking.services.interfaces;

import com.example.giel.parking.models.UserInfo;
import java.util.List;

public interface IUserInfoService {

  //To be used when a new user registers to get a newly created empty object for the ID to link
  Long createNewEmptyUser();

  List<UserInfo> getAllUserInfos();

}
