package com.example.giel.parking.models;

import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "UserCredentials")
@EqualsAndHashCode
@ToString
@Getter
@Setter
@NoArgsConstructor
public class UserCredential {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID")
  private Long id;

  @Column(name = "EMAIL")
  private String email;

  @Column(name = "PASSWORD")
  private String password;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinTable(name = "UserCredentials_Roles",
      joinColumns = @JoinColumn(name = "USERCREDENTIALS_ID", referencedColumnName = "ID"),
      inverseJoinColumns = @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID"))
  private Set<Role> roles;

  @Column(name = "USERINFO_ID")
  private Long userInfoId;

  @Column(name = "ACTIVE")
  private boolean active;

  public UserCredential(String email, String password, Set<Role> roles, Long userInfoId) {
    this.email = email;
    this.password = password;
    this.roles = roles;
    this.userInfoId = userInfoId;
    this.active = true;
  }

}
