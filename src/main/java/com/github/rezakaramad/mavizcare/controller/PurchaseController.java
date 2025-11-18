package com.github.rezakaramad.mavizcare.controller;

import com.github.rezakaramad.mavizcare.entity.Purchase;
import com.github.rezakaramad.mavizcare.repository.PurchaseRepository;
import com.github.rezakaramad.mavizcare.repository.FoodRepository;
import com.github.rezakaramad.mavizcare.repository.ProfileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller for managing purchases.
 *
 * @see Purchase
 * @see PurchaseRepository
 */
@Controller
@RequestMapping("/purchase")
public class PurchaseController {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private ProfileRepository profileRepository;

    /**
     * GET /purchase/list
     * Displays all purchases.
     */
    @GetMapping("/list")
    public String listPurchases(Model model) {
        model.addAttribute("purchases", purchaseRepository.findAllByOrderByPurchaseDateDesc());
        return "purchase-list"; // Thymeleaf template
    }

    /**
     * GET /purchase/add
     * Shows the form for creating a new purchase.
     *
     * Loads profiles + foods for dropdowns.
     */
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("purchase", new Purchase());
        model.addAttribute("foods", foodRepository.findAll());
        model.addAttribute("profiles", profileRepository.findAll());
        return "purchase-add"; // Thymeleaf template
    }

    /**
     * POST /purchase/add
     * Saves the submitted purchase into the database.
     */
    @PostMapping("/add")
    public String savePurchase(Purchase purchase) {
        purchaseRepository.save(purchase);
        return "redirect:/purchase/list";
    }

    /**
     * GET /purchase/{id}
     * Displays the details of a specific purchase.
     */
    @GetMapping("/{id}")
    public String viewPurchase(@PathVariable Long id, Model model) {
        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase not found with id " + id));

        model.addAttribute("purchase", purchase);
        return "purchase-details"; // Thymeleaf template
    }

    /**
     * POST /purchase/delete?id=5
     * Deletes a purchase by ID.
     */
    @PostMapping("/delete")
    public String deletePurchase(@RequestParam("id") Long id) {
        purchaseRepository.deleteById(id);
        return "redirect:/purchase/list";
    }
}
