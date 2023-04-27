package com.densoft.sdjpaintro.bootstrap;

import com.densoft.sdjpaintro.domain.Book;
import com.densoft.sdjpaintro.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"local", "default"})
@Component
public class DataInitializer implements CommandLineRunner {
    private final BookRepository bookRepository;

    public DataInitializer(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        bookRepository.deleteAll();
        Book bookDDD = new Book("Domain Driven Design", "123", "RandomHouse",null);
        Book savedDDD = bookRepository.save(bookDDD);

        Book bookSIA = new Book("Spring in Action", "213243", "Oriely",null);
        Book savedSIA = bookRepository.save(bookSIA);

        bookRepository.findAll().forEach(book -> {
            System.out.printf("Book Id: %d%n", book.getId());
            System.out.printf("Book Title: %s%n", book.getTitle());
            System.out.printf("Book Publisher: %s%n", book.getPublisher());
        });

    }
}
