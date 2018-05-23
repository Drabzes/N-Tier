package com.example.giel.parking.repositories.dao;

import com.example.giel.parking.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Role, Integer> {

  Role findByName(String roleName);

}
