package com.densoft.sdjpaintro.dao;

import com.densoft.sdjpaintro.domain.Book;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;

@Component
public class BookDaoImpl implements BookDao {

    @Override
    public Book getById(Long id) {
       return null;
    }


    @Override
    public Book findBookByTitle(String title) {
        return null;
    }

    @Override
    public Book saveNewBook(Book book) {
       return null;
    }

    @Override
    public Book updateBook(Book book) {
       return null;
    }

    @Override
    public void deleteBookById(Long id) {

    }

}
