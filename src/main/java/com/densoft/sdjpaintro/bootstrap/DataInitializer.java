package com.densoft.sdjpaintro.bootstrap;

import com.densoft.sdjpaintro.domain.Author;
import com.densoft.sdjpaintro.domain.AuthorUuid;
import com.densoft.sdjpaintro.domain.Book;
import com.densoft.sdjpaintro.domain.BookUuid;
import com.densoft.sdjpaintro.repositories.AuthorRepository;
import com.densoft.sdjpaintro.repositories.AuthorUuidRepository;
import com.densoft.sdjpaintro.repositories.BookRepository;
import com.densoft.sdjpaintro.repositories.BookUuidRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Profile({"local", "default"})
@Component
public class DataInitializer implements CommandLineRunner {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final AuthorUuidRepository authorUuidRepository;
    private final BookUuidRepository bookUuidRepository;

    public DataInitializer(BookRepository bookRepository, AuthorUuidRepository authorUuidRepository, BookUuidRepository bookUuidRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorUuidRepository = authorUuidRepository;
        this.bookUuidRepository = bookUuidRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        bookRepository.deleteAll();
        Book bookDDD = new Book("Domain Driven Design", "123", "RandomHouse", null);
        Book savedDDD = bookRepository.save(bookDDD);

        Book bookSIA = new Book("Spring in Action", "213243", "Oriely", null);
        Book savedSIA = bookRepository.save(bookSIA);

        bookRepository.findAll().forEach(book -> {
            System.out.printf("Book Id: %d%n", book.getId());
            System.out.printf("Book Title: %s%n", book.getTitle());
            System.out.printf("Book Publisher: %s%n", book.getPublisher());
        });

        IntStream.rangeClosed(1, 100).forEach(value -> {
            Book newBook = new Book("Book " + value, "Author " + value, "pub " + value, null);
            bookRepository.save(newBook);
        });

        AuthorUuid authorUuid = new AuthorUuid();
        authorUuid.setFirstName("Joe");
        authorUuid.setLastName("Buck");
        AuthorUuid savedAuthor = authorUuidRepository.save(authorUuid);
        System.out.printf("Saved Author UUID: %s", savedAuthor.getId());

        BookUuid bookUuid = new BookUuid();
        bookUuid.setTitle("All About UUIDs");
        BookUuid savedBookUuid = bookUuidRepository.save(bookUuid);
        System.out.printf("Saved Book UUID: %s", savedBookUuid.getId());

        Author author = new Author();
        author.setFirstName("dennis");
        author.setLastName("githinji");
        authorRepository.save(author);


    }
}
