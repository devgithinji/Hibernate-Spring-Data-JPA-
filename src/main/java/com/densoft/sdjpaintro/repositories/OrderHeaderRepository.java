package com.densoft.sdjpaintro.repositories;

import com.densoft.sdjpaintro.domain.OrderHeader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderHeaderRepository  extends JpaRepository<OrderHeader, Long> {
}
