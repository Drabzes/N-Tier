package com.example.giel.parking.repositories.dao;


import com.example.giel.parking.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Long> {

  UserInfo getUserInfoByUuid(String uuid);


}
