package com.team6.serviceme.controller;

import com.team6.serviceme.base.BaseResponse;
import com.team6.serviceme.base.ResultResponse;
import com.team6.serviceme.domain.Order;
import com.team6.serviceme.service.OrderService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Api(tags = "Order Management")
@RestController
public class OrderController {


    @Autowired
    private OrderService orderService;

    /**
     * createOrder
     * @param order
     * @return
     */
    @PostMapping("/order/createOrder")
    @ApiOperation(value = "createOrder")
    public BaseResponse<Order> createOrder(@Valid @RequestBody Order order, Authentication auth) throws AuthenticationException {
        order.setUserId((Long)auth.getPrincipal());
        return new ResultResponse<>(orderService.createNewOrder(order));
    }
}
