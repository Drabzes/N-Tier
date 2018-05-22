package com.example.giel.parking.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Privileges")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id",
    scope = Long.class)
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class Privilege {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @ManyToOne
  @JoinColumn(name = "USER_INFO_ID", referencedColumnName = "ID")
  @JsonBackReference
  private UserInfo userInfo;

  @NotNull(message = "{be.devoteam.entity.Privilege.serviceTypeId.NotNull}")
  @Column(name = "SERVICE_TYPE_ID")
  private Long serviceTypeId;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "privilege", cascade = CascadeType.ALL)
  private List<ServiceLimitInfo> serviceLimitInfoList;

  public Privilege(UserInfo userInfo,
      @NotNull(message = "{be.devoteam.entity.Privilege.serviceTypeId.NotNull}") Long serviceTypeId) {
    this.userInfo = userInfo;
    this.serviceTypeId = serviceTypeId;
    this.serviceLimitInfoList = Collections.singletonList(new ServiceLimitInfo(0L));
  }

  @Override
  public String toString() {
    return "Privilege{" +
        "id=" + id +
        ", userInfo=" + userInfo.getId() +
        ", serviceTypeId=" + serviceTypeId +
        ", serviceLimitInfoList=" + serviceLimitInfoList +
        '}';
  }
}
