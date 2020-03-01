package com.team6.serviceme.service;

import com.team6.serviceme.domain.Order;
import com.team6.serviceme.domain.User;

import java.util.List;

public interface OrderService {

    /**
     * createNewOrder
     * @param order
     */
    Order createNewOrder(Order order);

    /**
     * getCustomerOrderList
     * @param user
     */
    List<Order> getCustomerOrderList(User user);

    /**
     * getVendorOrderList
     * @param user
     */
    List<Order> getVendorOrderList(User user);
}
