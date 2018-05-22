package com.example.giel.parking.models;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "EntryHistory")
@EqualsAndHashCode
@ToString
@Getter
@Setter
@NoArgsConstructor
public class EntryHistory {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "ENTERING_TIME")
  private LocalDateTime enteringTime;

  @Column(name = "LEAVING_TIME")
  private LocalDateTime leavingTime;

  @Column(name = "USER_INFO_ID")
  private Long userInfoId;

  @Column(name = "CUSTOMER_INFO_ID")
  private Long customerInfoId;

  @Column(name = "SERVICE_TYPE_ID")
  private Long serviceTypeId;

  @Column(name = "SERVICE_ID")
  private Long serviceId;

  @Column(name = "ENTRY_COST")
  private Double entryCost;

  @Column(name = "INSIDE_PARKING")
  private boolean insideParking = true;

}
