package com.example.bookcatalog.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String language;
    private int downloads;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public String getTitle() {
        return this.title;
    }

    public Author getAuthor() {
        return this.author;
    }

    public void setTitle(String bookTitle) {
    }

    public void setAuthor(Author author) {
    }

    public void setLanguage(String language) {
    }

    public void setDownloads(int downloads) {
    }

    // Getters y Setters
}
