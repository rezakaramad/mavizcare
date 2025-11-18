package com.github.rezakaramad.mavizcare.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.math.BigInteger;

/**
 * Entity representing a location associated with a trip.
 * Each location is linked to a specific trip and stores the location name.
 * 
 * <p>This entity is used to store and manage trip location details in the database.
 * It contains a relationship to the Trip entity, representing the one-to-many relationship 
 * where a trip can have multiple locations.</p>
 *
 * @see Trips
 */
@Entity
public class TripLocations {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private BigInteger id;
    
  private String location;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "trip_id")
  private Trips trips;

  public TripLocations() {
  }

  public TripLocations(String location, Trips trip) {
    this.location = location;
    this.trips = trip;
  }

  public BigInteger getId() {
    return id;
  }

  public void setId(BigInteger id) {
    this.id = id;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public Trips getTrip() {
    return trips;
  }

  public void setTrip(Trips trip) {
    this.trips = trip;
  }
}
