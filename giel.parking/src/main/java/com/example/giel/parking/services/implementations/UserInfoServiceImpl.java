package com.example.giel.parking.services.implementations;

import com.example.giel.parking.models.UserInfo;
import com.example.giel.parking.repositories.dao.UserInfoRepository;
import com.example.giel.parking.services.interfaces.IUserInfoService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoServiceImpl implements IUserInfoService {


  private Logger logger = LoggerFactory.getLogger(this.getClass());


  @Autowired
  private UserInfoRepository userInfoRepository;


  @Override
  public Long createNewEmptyUser() {
    UserInfo userInfo = new UserInfo();
    return saveAndFlushUserInfo(userInfo).getId();
  }

  @Override
  public List<UserInfo> getAllUserInfos() {
    return userInfoRepository.findAll();
  }

  @Override
  public String getUuidByUserInfoId(Long userInfoId) {
    return userInfoRepository.findById(userInfoId).get().getUuid();
  }

  @Override
  public UserInfo getUserInfoByUuid(String uuid) {
    return null;
  }


  public UserInfo saveAndFlushUserInfo(UserInfo userInfoToSave) {
    return userInfoRepository.saveAndFlush(userInfoToSave);
  }
}
