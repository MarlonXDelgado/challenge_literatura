package com.example.bookcatalog.service;

import com.example.bookcatalog.model.Author;
import com.example.bookcatalog.model.Book;
import com.example.bookcatalog.repository.AuthorRepository;
import com.example.bookcatalog.repository.BookRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public Book searchBookByTitle(String title) throws IOException {
        String url = "https://gutendex.com/books/?title=" + title.replace(" ", "%20");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(new URL(url));

        if (root.path("count").asInt() == 0) {
            throw new BookNotFoundException("No se encontró ningún libro con el título especificado: " + title);
        }

        JsonNode bookNode = root.path("results").get(0);

        String bookTitle = bookNode.path("title").asText();
        JsonNode authorNode = bookNode.path("authors").get(0);
        String authorName = authorNode.path("name").asText();
        Integer birthYear = authorNode.path("birth_year").isNull() ? null : authorNode.path("birth_year").asInt();
        Integer deathYear = authorNode.path("death_year").isNull() ? null : authorNode.path("death_year").asInt();
        String language = bookNode.path("languages").get(0).asText();
        int downloads = bookNode.path("download_count").asInt();

        Author author = new Author();
        author.setName(authorName);
        author.setBirthYear(birthYear);
        author.setDeathYear(deathYear);

        Book book = new Book();
        book.setTitle(bookTitle);
        book.setAuthor(author);
        book.setLanguage(language);
        book.setDownloads(downloads);

        authorRepository.save(author);
        return bookRepository.save(book);
    }

    public List<Book> listAllBooks() {
        return bookRepository.findAll();
    }

    public List<Book> listBooksByLanguage(String language) {
        return bookRepository.findByLanguage(language);
    }
}
