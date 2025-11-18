package com.github.rezakaramad.mavizcare.controller;

import com.github.rezakaramad.mavizcare.entity.Trips;
import com.github.rezakaramad.mavizcare.repository.TripsRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Controller class for handling requests related to trips.
 * <p>
 * This class manages the retrieval and display of trips stored in the database. 
 * It is responsible for mapping HTTP requests to appropriate handler methods and 
 * returning views to be rendered.
 * </p>
 * 
 * <p>
 * The main functionality provided by this controller is displaying a list of all trips 
 * available in the system.
 * </p>
 *
 * @see Trips
 * @see TripsRepository
 */
@Controller
@RequestMapping("/trips")
public class TripsController {

  @Autowired
  private TripsRepository tripsRepository;

  /**
   * Retrieves all trips and adds them to the model for display.
   *
   * <p>This method handles HTTP GET requests to the "/trips" endpoint. It fetches a list of all 
   * trips from the repository and adds this list to the model with the key "trips" for rendering 
   * in the view. The method then returns the name of the view template to be rendered, 
   * which is "trips".
   *
   * @param model the model object used to pass data to the view
   * @return the name of the view template ("trips")
   */
  @GetMapping
  public String getAllTrips(Model model) {
    List<Trips> trips = tripsRepository.findAll();
    model.addAttribute("trips", trips);
    return "trips";
  }
}
