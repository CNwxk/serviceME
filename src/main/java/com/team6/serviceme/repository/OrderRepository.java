package com.team6.serviceme.repository;

import com.team6.serviceme.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
