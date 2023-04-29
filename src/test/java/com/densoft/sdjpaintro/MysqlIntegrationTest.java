package com.densoft.sdjpaintro;

import com.densoft.sdjpaintro.domain.AuthorUuid;
import com.densoft.sdjpaintro.domain.BookNatural;
import com.densoft.sdjpaintro.domain.BookUuid;
import com.densoft.sdjpaintro.domain.composite.AuthorComposite;
import com.densoft.sdjpaintro.domain.composite.AuthorEmbedded;
import com.densoft.sdjpaintro.domain.composite.NameId;
import com.densoft.sdjpaintro.repositories.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("local")
@DataJpaTest
@ComponentScan(basePackages = {"com.densoft.sdjpaintro.bootstrap"})
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MysqlIntegrationTest {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    AuthorUuidRepository authorUuidRepository;
    @Autowired
    BookUuidRepository bookUuidRepository;
    @Autowired
    BookNaturalRepository bookNaturalRepository;
    @Autowired
    AuthorCompositeRepository authorCompositeRepository;
    @Autowired
    AuthorEmbeddedRepository authorEmbeddedRepository;

    @Test
    void authorEmbeddedTest() {
        NameId nameId = new NameId("John", "T");
        AuthorEmbedded authorEmbedded = new AuthorEmbedded(nameId);
        AuthorEmbedded saved  = authorEmbeddedRepository.save(authorEmbedded);
        assertThat(saved).isNotNull();
        Optional<AuthorEmbedded> fetched = authorEmbeddedRepository.findById(nameId);
        assertThat(fetched).isPresent();
    }

    @Test
    void authorCompositeTest() {
        NameId nameId = new NameId("John", "T");
        AuthorComposite authorComposite = new AuthorComposite();
        authorComposite.setFirstName(nameId.getFirstName());
        authorComposite.setLastName(nameId.getLastName());
        authorComposite.setCountry("US");
        AuthorComposite saved = authorCompositeRepository.save(authorComposite);
        assertThat(saved).isNotNull();
        Optional<AuthorComposite> fetched = authorCompositeRepository.findById(nameId);
        assertThat(fetched).isPresent();
    }

    @Test
    void bookNaturalTest() {
        BookNatural bookNatural = new BookNatural();
        bookNatural.setTitle("My Book");
        BookNatural saved = bookNaturalRepository.save(bookNatural);
        Optional<BookNatural> fetched = bookNaturalRepository.findById(saved.getTitle());
        assertThat(fetched).isPresent();
    }

    @Test
    void testBookUuid() {
        BookUuid bookUuid = bookUuidRepository.save(new BookUuid());
        assertThat(bookUuid).isNotNull();
        assertThat(bookUuid.getId()).isNotNull();

        Optional<BookUuid> fetched = bookUuidRepository.findById(bookUuid.getId());
        assertThat(fetched).isNotNull();
    }

    @Test
    void testAuthorUuid() {
        AuthorUuid authorUuid = authorUuidRepository.save(new AuthorUuid());
        assertThat(authorUuid).isNotNull();
        assertThat(authorUuid.getId()).isNotNull();

        Optional<AuthorUuid> fetched = authorUuidRepository.findById(authorUuid.getId());
        assertThat(fetched).isPresent();
    }

    @Test
    void testMySQL() {
        long countBefore = bookRepository.count();
        assertThat(countBefore).isEqualTo(2);
    }
}
