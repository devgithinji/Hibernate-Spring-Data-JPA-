package com.densoft.sdjpaintro.repositories;

import com.densoft.sdjpaintro.domain.BookUuid;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookUuidRepository extends JpaRepository<BookUuid, UUID> {
}
