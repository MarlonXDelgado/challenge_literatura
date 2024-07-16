package com.example.bookcatalog.service;

import com.example.bookcatalog.model.Author;
import com.example.bookcatalog.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    public List<Author> listAllAuthors() {
        return authorRepository.findAll();
    }

    public List<Author> listLivingAuthorsInYear(int year) {
        return authorRepository.findByBirthYearLessThanEqualAndDeathYearGreaterThanEqual(year, year);
    }
}
