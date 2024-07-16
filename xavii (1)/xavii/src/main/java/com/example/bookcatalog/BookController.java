package com.example.bookcatalog;

import com.example.bookcatalog.model.Author;
import com.example.bookcatalog.model.Book;
import com.example.bookcatalog.service.AuthorService;
import com.example.bookcatalog.service.BookNotFoundException;
import com.example.bookcatalog.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;

@Controller


public class BookController implements CommandLineRunner {

    private final BookService bookService;
    private final AuthorService authorService;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @Override
    public void run(String... args) {

        Scanner scanner = new Scanner(System.in);
        int option;

        do {
            System.out.println("Elija la opción a través de su número:");
            System.out.println("1- buscar libro por título");
            System.out.println("2- listar libros registrados");
            System.out.println("3- listar autores registrados");
            System.out.println("4- listar autores vivos en un determinado año");
            System.out.println("5- listar libros por idioma");
            System.out.println("0- salir");
            option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.println("Ingrese el título del libro:");
                    String title = scanner.nextLine();
                    try {
                        Book book = bookService.searchBookByTitle(title);
                        System.out.println("Libro encontrado: " + book.getTitle() + " por " + book.getAuthor().getName());
                    } catch (BookNotFoundException e) {
                        System.out.println("No se encontró ningún libro con el título especificado.");
                    } catch (Exception e) {
                        System.out.println("Error al buscar el libro: " + e.getMessage());
                    }
                    break;
                case 2:
                    List<Book> books = bookService.listAllBooks();
                    books.forEach(b -> System.out.println(b.getTitle() + " por " + b.getAuthor().getName()));
                    break;
                case 3:
                    List<Author> authors = authorService.listAllAuthors();
                    authors.forEach(a -> System.out.println(a.getName() + " (Nacido: " + a.getBirthYear() + ", Fallecido: " + a.getDeathYear() + ")"));
                    break;
                case 4:
                    System.out.println("Ingrese el año:");
                    int year = scanner.nextInt();
                    List<Author> livingAuthors = authorService.listLivingAuthorsInYear(year);
                    livingAuthors.forEach(a -> System.out.println(a.getName() + " (Nacido: " + a.getBirthYear() + ", Fallecido: " + a.getDeathYear() + ")"));
                    break;
                case 5:
                    System.out.println("Ingrese el idioma:");
                    String language = scanner.nextLine();
                    List<Book> booksByLanguage = bookService.listBooksByLanguage(language);
                    booksByLanguage.forEach(b -> System.out.println(b.getTitle() + " por " + b.getAuthor().getName()));
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        } while (option != 0);

        scanner.close();
    }
}
