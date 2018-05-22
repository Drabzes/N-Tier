package com.example.giel.parking.models;


import static com.example.giel.parking.enums.TimeLimit.ALL;

import com.example.giel.parking.enums.TimeLimit;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.time.DayOfWeek;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "ServiceLimitInfo")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id",
    scope = Long.class)
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class ServiceLimitInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "SERVICE_ID")
  private Long serviceId;

  @ElementCollection
  @Column(name = "TIME_LIMIT")
  private Map<DayOfWeek, TimeLimit> days;

  @ManyToOne
  @JoinColumn(name = "PRIVILEGE_ID", referencedColumnName = "ID")
  @JsonBackReference
  private Privilege privilege;

  public ServiceLimitInfo(Long serviceId) {
    this.serviceId = serviceId;
    Map<DayOfWeek, TimeLimit> days = new HashMap<>();
    Arrays.asList(DayOfWeek.values())
        .forEach(day -> days.put(day, ALL));
    this.days = days;
  }

  @Override
  public String toString() {
    return "ServiceLimitInfo{" +
        "id=" + id +
        ", serviceId=" + serviceId +
        ", days=" + days +
        ", privilege=" + privilege.getId() +
        '}';
  }

}
