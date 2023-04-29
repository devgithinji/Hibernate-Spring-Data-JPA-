package com.densoft.sdjpaintro.repositories;

import com.densoft.sdjpaintro.domain.composite.AuthorComposite;
import com.densoft.sdjpaintro.domain.composite.NameId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorCompositeRepository extends JpaRepository<AuthorComposite, NameId> {
}
