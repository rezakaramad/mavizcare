package com.github.rezakaramad.mavizcare.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entity representing a book with a title and an author.
 * 
 * <p>This class is used to store and manage book details in the database. Each book
 * has a unique identifier, a title, and an author.</p>
 */
@Entity
public class Books {
  @Id
  @GeneratedValue(strategy =  GenerationType.IDENTITY)
  private Long id;
  private String title;
  private String author;

  public Books() {
  }

  public Books(String title, String author) {
    this.title = title;
    this.author = author;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }
}

