package com.densoft.sdjpaintro.repositories;

import com.densoft.sdjpaintro.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
