package com.densoft.sdjpaintro.repositories;

import com.densoft.sdjpaintro.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
