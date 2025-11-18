package com.github.rezakaramad.mavizcare.repository;

import com.github.rezakaramad.mavizcare.entity.Trips;
import java.math.BigInteger;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Trip} entities.
 *
 * <p>This interface extends {@link JpaRepository}, providing standard CRUD operations 
 * and additional query capabilities for the {@link Trip} entity. It uses {@link BigInteger}
 * as the type for the entity's primary key.
 *
 * <p>Spring Data JPA will automatically generate the implementation at runtime, allowing
 * for easy data access and manipulation of trip records in the database.
 */
public interface TripsRepository extends JpaRepository<Trips, BigInteger> {
}
