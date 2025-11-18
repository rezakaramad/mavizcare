package com.github.rezakaramad.mavizcare.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.math.BigInteger;
import java.util.Date;

/**
 * Entity representing a user in the system.
 * 
 * <p>This class stores basic information about a user, including a unique identifier, 
 * the date the user was created, and the user's email address. It is mapped to a 
 * database table using JPA annotations.</p>
 * 
 * <p>The {@code User} entity is designed to be used in conjunction with other entities 
 * in the application, and serves as the primary representation of a user in the database.</p>
 */
@Entity
public class Users {
  @Id
  @GeneratedValue(strategy =  GenerationType.IDENTITY)
  private BigInteger id;

  @Column(name = "created_at")
  private Date createdAt;
  
  private String email;
  
  public Users() {
  }

  public Users(Date createdAt, String email) {
    this.createdAt = createdAt;
    this.email = email;
  }

  public BigInteger getId() {
    return id;
  }

  public void setId(BigInteger id) {
    this.id = id;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
