package com.example.giel.parking.repositories.dao;

import com.example.giel.parking.models.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialsRepository extends JpaRepository<UserCredential, Long> {

  UserCredential findByEmail(String email);

}
