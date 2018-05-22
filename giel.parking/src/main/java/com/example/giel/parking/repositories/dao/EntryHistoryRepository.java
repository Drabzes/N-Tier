package com.example.giel.parking.repositories.dao;

import com.example.giel.parking.models.EntryHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EntryHistoryRepository extends JpaRepository<EntryHistory, Long> {

  EntryHistory getByUserInfoIdAndInsideParkingAndServiceId(Long userInfoId,
      boolean insideParkingStatus, Long serviceId);

  EntryHistory getByUserInfoIdAndInsideParking(Long userInfoId, boolean insideParkingStatus);

  List<EntryHistory> findAllByUserInfoId(Long userInfoId);

  List<EntryHistory> findAllByUserInfoIdAndInsideParking(Long userInfoId,
      boolean insideParkingStatus);

  List<EntryHistory> findAllByCustomerInfoId(Long userInfoId);

  List<EntryHistory> findAllByUserInfoIdAndCustomerInfoIdOrCustomerInfoId(Long userInfoId,
      Long nullId, Long customerInfoId);
}
