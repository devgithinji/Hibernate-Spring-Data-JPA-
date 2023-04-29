package com.densoft.sdjpaintro.repositories;

import com.densoft.sdjpaintro.domain.composite.AuthorEmbedded;
import com.densoft.sdjpaintro.domain.composite.NameId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorEmbeddedRepository extends JpaRepository<AuthorEmbedded, NameId> {
}
