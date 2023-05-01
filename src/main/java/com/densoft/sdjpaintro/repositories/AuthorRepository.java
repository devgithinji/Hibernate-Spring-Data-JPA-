package com.densoft.sdjpaintro.repositories;

import com.densoft.sdjpaintro.domain.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
