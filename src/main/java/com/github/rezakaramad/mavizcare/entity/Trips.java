package com.github.rezakaramad.mavizcare.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Represents a trip entity in the system.
 * A Trip has a start date, an end date, a user associated with it, 
 * and a list of locations visited during the trip.
 * The Trip entity is linked to multiple TripLocation entities through a one-to-many relationship.
 */
@Entity
public class Trips {
  @Id
  @GeneratedValue(strategy =  GenerationType.IDENTITY)
  private BigInteger id;

  @Column(name = "from_date")
  private Date fromDate;

  @Column(name = "to_date")
  private Date toDate;

  @Column(name = "user_id")
  private BigInteger userId;
  
  @OneToMany(mappedBy = "trips", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  private List<TripLocations> locations;
  
  public Trips() {
  }

  /**
   * Constructs a new Trip object with the specified 
   * start date, end date, user ID, and list of locations.
   *
   * @param fromDate The start date of the trip.
   * @param toDate The end date of the trip.
   * @param userId The ID of the user associated with the trip.
   * @param locations The list of locations associated with the trip.
   */
  public Trips(Date fromDate, Date toDate, BigInteger userId, List<TripLocations> locations) {
    this.fromDate = fromDate;
    this.toDate = toDate;
    this.userId = userId;
    this.locations = locations;
  }
  
  public BigInteger getId() {
    return id;
  }
  
  public void setId(BigInteger id) {
    this.id = id;
  }
  
  public String getFrom() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    return sdf.format(fromDate);
  }
  
  public void setFrom(Date fromDate) {
    this.fromDate = fromDate;
  }
  
  public String getTo() {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    return sdf.format(toDate);
  }
  
  public void setTo(Date toDate) {
    this.toDate = toDate;
  }
  
  public BigInteger getUserId() {
    return userId;
  }
  
  public void setUerId(BigInteger userId) {
    this.userId = userId;
  }
  
  public List<TripLocations> getLocations() {
    return locations;
  }
  
  public void setLocations(List<TripLocations> locations) {
    this.locations = locations;
  }
}
