package com.team6.serviceme.controller;

import com.team6.serviceme.base.BaseResponse;
import com.team6.serviceme.base.ResultResponse;
import com.team6.serviceme.domain.Order;
import com.team6.serviceme.domain.User;
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
import java.util.List;

@Api(tags = "Order Management")
@RestController
public class OrderController {


    @Autowired
    private OrderService orderService;

    /**
     * createOrder
     * @param order
     * @param auth
     * @return
     */
    @PostMapping("/order/createOrder")
    @ApiOperation(value = "createOrder")
    public BaseResponse<Order> createOrder(@Valid @RequestBody Order order, Authentication auth) throws AuthenticationException {
        User u = (User) auth.getPrincipal();
        Long id = u.getId();
        order.setUserId(id);
        return new ResultResponse<>(orderService.createNewOrder(order));
    }

    /**
     * getCustomerOrderList
     * @param auth
     * @return
     */
    @PostMapping("/order/getCustomerOrderList")
    @ApiOperation(value="getCustomerOrderList")
    public BaseResponse<List<Order>> getCustomerOrderList(Authentication auth) throws AuthenticationException{
        User u = (User) auth.getPrincipal();
        return new ResultResponse<>(orderService.getCustomerOrderList(u));
    }

    /**
     * getCustomerOrderList
     * @param auth
     * @return
     */
    @PostMapping("/order/getVendorOrderList")
    @ApiOperation(value="getVendorOrderList")
    public BaseResponse<List<Order>> getVendorOrderList(Authentication auth) throws AuthenticationException{
        User u = (User) auth.getPrincipal();
        return new ResultResponse<>(orderService.getVendorOrderList(u));
    }

}
