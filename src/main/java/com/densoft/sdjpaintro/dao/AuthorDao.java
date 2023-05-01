package com.densoft.sdjpaintro.dao;

import com.densoft.sdjpaintro.domain.Author;

public interface AuthorDao {
    Author getById(Long id);
    Author findAuthorByName(String firstName, String lastName);
    Author saveNewAuthor(Author author);

    Author updateAuthor(Author saved);

    void deleteAuthorById(Long id);
}
