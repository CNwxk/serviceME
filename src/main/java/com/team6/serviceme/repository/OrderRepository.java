package com.team6.serviceme.repository;

import com.team6.serviceme.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "select * from orders as o where o.user_id=?1 order by o.creat_time desc", nativeQuery = true)
    List<Order> findByUserId(Long userId);

    @Query(value = "select * from orders as o where o.order_type=?1 order by o.creat_time desc", nativeQuery = true)
    List<Order> findByOrderType(String orderType);

}
