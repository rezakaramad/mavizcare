package com.github.rezakaramad.mavizcare.controller;

import com.github.rezakaramad.mavizcare.entity.Books;
import com.github.rezakaramad.mavizcare.repository.BooksRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


/**
 * Controller for managing books in the application.
 * Handles HTTP requests related to viewing, creating, and deleting books.
 *
 * <p>This controller provides methods to:
 * <ul>
 *   <li>Display a list of all books.</li>
 *   <li>Show a form for creating a new book.</li>
 *   <li>Save a new book to the database.</li>
 *   <li>Display details of a specific book.</li>
 *   <li>Delete a book by its ID.</li>
 * </ul>
 *
 * <p>Views are resolved from the 'src/main/resources/templates' directory using Thymeleaf.
 *
 * @see Books
 * @see BooksRepository
 */
@Controller
@RequestMapping("/books")
public class BooksController {

  @Autowired
  private BooksRepository booksRepository;

  /**
   * Handles GET requests to retrieve all books from the repository.
   * 
   * <p>This method fetches a list of all books from the database using the
   * {@link BooksRepository} and adds it to the model. The model is then passed
   * to the 'books.html' template for rendering.
   *
   * @param model The model object to pass data to the view.
   * @return The name of the Thymeleaf template to render, in this case 'books'.
   * @see BooksRepository
   */
  @GetMapping
  public String getAllBooks(Model model) {
    List<Books> books = booksRepository.findAll();
    model.addAttribute("books", books);
    return "books";
  }


  @GetMapping("/new")
  public String showForm(Model model) {
    model.addAttribute("book", new Books());
    return "book-new";
  }

  @PostMapping
  public String submitBook(Books books) {
    booksRepository.save(books); // saves the new book to the database
    return "redirect:/books"; // redirects to the listing page which needs to be created
  }

  /**
   * Retrieves and displays the details of a specific book by its ID.
   *
   * <p>This method handles GET requests to "/books/{id}". 
   * It attempts to find a book with the given ID.
   * If the book is found, it adds the book to the model and returns the "book-details" view.
   * If the book is not found, a {@code RuntimeException} is thrown.
   *
   * @param id The ID of the book to retrieve.
   * @param model The model to add attributes to, for rendering the view.
   * @return The name of the view to display the book details.
   */
  @GetMapping("/{id}")
  public String getBookDetails(@PathVariable Long id, Model model) {
    Books books = booksRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Book not found with id " + id));
    model.addAttribute("book", books);
    return "book-details"; 
  }

  @PostMapping("/delete")
  public String deleteBook(@RequestParam("id") Long id) {
    booksRepository.deleteById(id);
    return "redirect:/books"; 
  }
}