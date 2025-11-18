package com.github.rezakaramad.mavizcare.controller;

import com.github.rezakaramad.mavizcare.entity.Food;
import com.github.rezakaramad.mavizcare.repository.FoodRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for managing food.
 *
 * @see Food
 * @see foodRepository
 */
@Controller
@RequestMapping("/food")
public class FoodController {

    @Autowired
    private FoodRepository foodRepository;

    /**
     * GET /food/list
     * Displays all foods.
     */
    @GetMapping("/list")
    public String listFoods(Model model) {
        model.addAttribute("foods", foodRepository.findAll());
        return "food/list"; // Thymeleaf template
    }

    /**
     * GET /food/add
     * Shows the form for creating a new food.
     *
     * Loads profiles + foods for dropdowns.
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("food", new Food());
        return "food/add"; // Thymeleaf template
    }

    /**
     * POST /food/add
     * Saves the submitted food into the database.
     */
    @PostMapping("/add")
    public String saveFood(Food food) {
        foodRepository.save(food);
        return "redirect:/food/list";
    }

    /**
     * GET /food/{id}
     * Displays the details of a specific food.
     */
    @GetMapping("/{id}")
    public String viewFood(@PathVariable Long id, Model model) {
        Food food = foodRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food not found with id " + id));

        model.addAttribute("food", food);
        return "food/details"; // Thymeleaf template
    }

    /**
     * POST /food/delete?id=5
     * Deletes a food by ID.
     */
    @PostMapping("/delete")
    public String deleteFood(@RequestParam("id") Long id) {
        foodRepository.deleteById(id);
        return "redirect:/food/list";
    }
}
