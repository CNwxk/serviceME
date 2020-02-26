package com.team6.serviceme.service.impl;

import com.team6.serviceme.domain.Order;
import com.team6.serviceme.repository.OrderRepository;
import com.team6.serviceme.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createNewOrder(Order order){
        long orderNo = this.generateOrderNo();
        order.setOrderId(orderNo);
        return orderRepository.save(order);
    }

    private long generateOrderNo(){
        long currentTime = System.currentTimeMillis();
        return currentTime + new Random().nextInt(100);
    }
}
