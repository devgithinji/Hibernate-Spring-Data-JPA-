package com.densoft.sdjpaintro;

import com.densoft.sdjpaintro.dao.AuthorDao;
import com.densoft.sdjpaintro.dao.BookDao;
import com.densoft.sdjpaintro.domain.Author;
import com.densoft.sdjpaintro.domain.Book;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"com.densoft.sdjpaintro.dao"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DaoIntegrationTest {
    @Autowired
    AuthorDao authorDao;
    @Autowired
    BookDao bookDao;

    @Test
    void findAllAuthors() {
        List<Author> authors = authorDao.findAll();

        assertThat(authors).isNotNull();
        assertThat(authors.size()).isGreaterThan(0);
    }

    @Test
    void testFindBookByISBN() {
        Book newBook = new Book();
        newBook.setIsbn("1234" + RandomString.make());
        newBook.setTitle("ISBN TEST");

        Book saved = bookDao.saveNewBook(newBook);

        Book fetchedBook = bookDao.findByISBN(newBook.getIsbn());

        assertThat(fetchedBook).isNotNull();
    }

    @Test
    void testListAuthorByLastNameLike() {
        Author author = new Author();
        author.setFirstName("test");
        author.setLastName("Walls");
        authorDao.saveNewAuthor(author);

        List<Author> authors = authorDao.listAuthorByLastNameLike("Wall");
        assertThat(authors).isNotNull();
        assertThat(authors.size()).isGreaterThan(0);
    }

    @Test
    void testDeleteBook() {
        Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("my book");
        book.setAuthorId(3L);
        Book saved = bookDao.saveNewBook(book);

        bookDao.deleteBookById(saved.getId());

        assertThrows(EmptyResultDataAccessException.class, () -> {
            bookDao.getById(saved.getId());
        });
    }

    @Test
    void updateBookTest() {
        Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("my book");
        book.setAuthorId(3L);
        Book saved = bookDao.saveNewBook(book);

        saved.setTitle("New Book");
        bookDao.updateBook(saved);

        Book fetched = bookDao.getById(saved.getId());

        assertThat(fetched.getTitle()).isEqualTo("New Book");
    }

    @Test
    void testSaveBook() {
        Book book = new Book();
        book.setIsbn("1234");
        book.setPublisher("Self");
        book.setTitle("my book");
        book.setAuthorId(3L);
        Book saved = bookDao.saveNewBook(book);

        assertThat(saved).isNotNull();
    }

    @Test
    void testGetBookByName() {
        Book book = bookDao.findBookByTitle("Clean Code");

        assertThat(book).isNull();
    }

    @Test
    void testGetBook() {
        Book book = bookDao.getById(1L);

        assertThat(book.getId()).isNotNull();
    }

    @Test
    void testDeleteAuthor() {
        Author author = new Author();
        author.setFirstName("john");
        author.setLastName("t");

        Author saved = authorDao.saveNewAuthor(author);
        System.out.println(saved.getId());
        authorDao.deleteAuthorById(saved.getId());

        Author deleted = authorDao.getById(saved.getId());

        assertThat(deleted).isNull();
    }

    @Test
    void testUpdateAuthor() {
        Author author = new Author();
        author.setFirstName("john");
        author.setLastName("t");

        Author saved = authorDao.saveNewAuthor(author);
        saved.setLastName("Thompson");
        Author updated = authorDao.updateAuthor(saved);
        assertThat(updated.getLastName()).isEqualTo("Thompson");
    }

    @Test
    void testSaveAuthor() {
        Author author = new Author();
        author.setFirstName("John");
        author.setLastName("Thompson");
        Author savedAuthor = authorDao.saveNewAuthor(author);
        assertThat(savedAuthor).isNotNull();
        assertThat(savedAuthor.getId()).isNotNull();
    }

    @Test
    void testGetAuthorByNameCriteria() {
        Author author = authorDao.findAuthorByNameCriteria("Craig", "Walls");
        assertThat(author).isNotNull();
    }

    @Test
    void testGetAuthorByNameNative() {
        Author author = authorDao.findAuthorByNameNative("Craig", "Walls");
        assertThat(author).isNull();
    }

    @Test
    void testGetAuthorByName() {
        Author author = authorDao.findAuthorByName("Craig", "Walls");
        assertThat(author).isNull();
    }

    @Test
    void testGetAuthor() {
        Author author = authorDao.getById(1L);
        assertThat(author).isNotNull();
    }
}
