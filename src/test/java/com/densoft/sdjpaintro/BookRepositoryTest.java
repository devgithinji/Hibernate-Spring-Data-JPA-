package com.densoft.sdjpaintro;

import com.densoft.sdjpaintro.domain.Book;
import com.densoft.sdjpaintro.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"com.densoft.sdjpaintro.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    @Test
    void testBookJPANamed() {
        Book book = bookRepository.jpaNamed("Clean Code");
        assertThat(book).isNotNull();
    }

    @Test
    void testBookNativeQuery() {
        Book book = bookRepository.findBookByTitleWithNativeQuery("Clean Code");
        assertThat(book).isNotNull();
    }
    @Test
    void testBookQueryNamed() {
        Book book = bookRepository.findBookByTitleWithQueryNamed("Clean Code");
        assertThat(book).isNotNull();
    }

    @Test
    void testBookQuery() {
        Book book = bookRepository.findBookByTitleWithQuery("Clean Code");
        assertThat(book).isNotNull();
    }

    @Test
    void testBookFuture() throws ExecutionException, InterruptedException {
        Future<Book> bookFuture = bookRepository.queryByTitle("Clean Code");
        Book book = bookFuture.get();
        assertNotNull(book);
    }

    @Test
    void testEmptyResultException() {
        assertThrows(EmptyResultDataAccessException.class, () -> {
            Book book = bookRepository.readByTitle("foobar4");
        });
    }

    @Test
    void testNullParameter() {
        assertNull(bookRepository.getByTitle(null));
    }

    @Test
    void testNoException() {
        assertNull(bookRepository.getByTitle("foo"));
    }

    @Test
    void testBookStream() {
        AtomicInteger count = new AtomicInteger();
        bookRepository.findAllByTitleNotNull().forEach(book -> {
            count.incrementAndGet();
        });

        assertThat(count.get()).isGreaterThan(5);
    }
}
