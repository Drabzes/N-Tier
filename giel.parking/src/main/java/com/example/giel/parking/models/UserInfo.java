package com.example.giel.parking.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "UserInfo")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id",
    scope = Long.class)
@EqualsAndHashCode
@Getter
@Setter
public class UserInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "FIRSTNAME")
  private String firstName;

  @Column(name = "LASTNAME")
  private String lastName;

  @Column(name = "UUID")
  private String uuid;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "CUSTOMER_ID")
  @JsonBackReference
  private UserInfo customer;

  @OneToMany(mappedBy = "customer")
  private List<UserInfo> beneficiaries;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "userInfo")
  private List<Privilege> privileges;

  @ElementCollection(fetch = FetchType.EAGER)
  @Column(name = "SERVICE_TYPES_ID")
  private List<Long> serviceTypes;

  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "UserInfo_PaymentMethods",
      joinColumns = @JoinColumn(name = "USER_INFO_ID", referencedColumnName = "ID"),
      inverseJoinColumns = @JoinColumn(name = "PAYMENT_METHOD_ID", referencedColumnName = "ID"))
  private List<PaymentMethod> paymentMethods;

  public UserInfo() {
    this.uuid = UUID.randomUUID().toString();
  }

  @Override
  public String toString() {
    return "UserInfo{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", uuid='" + uuid + '\'' +
        ", customer=" + customer.getId() +
        ", beneficiaries=" + beneficiaries +
        ", privileges=" + privileges +
        ", serviceTypes=" + serviceTypes +
        ", paymentMethods=" + paymentMethods +
        '}';
  }
}
