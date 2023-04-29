package com.densoft.sdjpaintro;

import com.densoft.sdjpaintro.repositories.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("local")
class SdjpaIntroApplicationTests {

    @Autowired
    BookRepository bookRepository;

    @Test
    void textBookRepository(){
        long count = bookRepository.count();
        assertThat(count).isGreaterThan(0);
    }

    @Test
    void contextLoads() {
    }

}
