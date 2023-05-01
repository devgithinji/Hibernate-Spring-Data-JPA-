package com.densoft.sdjpaintro.dao;

import com.densoft.sdjpaintro.domain.Author;

import java.util.List;

public interface AuthorDao {
    List<Author> listAuthorByLastNameLike(String lastName);
    Author getById(Long id);
    Author findAuthorByName(String firstName, String lastName);
    Author saveNewAuthor(Author author);

    Author updateAuthor(Author saved);

    void deleteAuthorById(Long id);

    List<Author> findAll();

    Author findAuthorByNameCriteria(String craig, String walls);

    Author findAuthorByNameNative(String craig, String walls);
}
