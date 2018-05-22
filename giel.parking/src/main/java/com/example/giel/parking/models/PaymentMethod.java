package com.example.giel.parking.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PaymentMethods")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
    property = "id",
    scope = Long.class)
@EqualsAndHashCode
@Getter
@Setter
@NoArgsConstructor
public class PaymentMethod {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @NotNull(message = "{be.devoteam.entity.PaymentMethod.name.NotNull}")
  @NotEmpty(message = "{be.devoteam.entity.PaymentMethod.name.NotEmpty}")
  @Column(name = "NAME", unique = true)
  private String name;

  @JsonBackReference
  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "paymentMethods")
  private List<UserInfo> users;

  @Override
  public String toString() {
    return "PaymentMethod{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", users=" + users.stream().map(UserInfo::getId).collect(Collectors.toList()) +
        '}';
  }
}

