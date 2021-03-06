package com.team6.serviceme.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

/**
 * Set Order Attributes
 *
 */
@Data   //Auto add Getter and Setter
@Entity
@ApiModel(description="Order Entity")
@NoArgsConstructor
@Table(name="orders")
public class Order {
    @ApiModelProperty(value = "Id", position = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ApiModelProperty(value = "Order Id", position = 2)
    private Long orderId;

    @ApiModelProperty(value = "User Id", position = 3)
    private Long userId;

    @ApiModelProperty(value = "Order Type", position = 4)
    @NonNull
    private String orderType;

    @ApiModelProperty(value = "Problem Description", position = 5)
    @NonNull
    private String problemDescription;

    @ApiModelProperty(value = "Customer Can Accept Min Price", position = 6)
    @NonNull
    private Integer minPrice;

    @ApiModelProperty(value = "Customer Can Accept Max Price", position = 7)
    @NonNull
    private Integer maxPrice;

    @ApiModelProperty(value = "Order State Address", position = 8)
    @NonNull
    private String orderState;

    @ApiModelProperty(value = "Order City Address", position = 9)
    @NonNull
    private String orderCity;

    @ApiModelProperty(value = "Order Detail Address", position = 10)
    @NonNull
    private String orderDetailAddress;

    @ApiModelProperty(value = "Order Zip Code", position = 11)
    @NonNull
    private String orderZipCode;

    @ApiModelProperty(value = "Phone Number", position = 12)
    @NonNull
    private String orderPhone;

    @ApiModelProperty(value = "Order Status", position = 13)
    private Integer orderStatus;

    @ApiModelProperty(value = "Customer Accept Price", position = 14)
    private String finalPrice;

    @ApiModelProperty(value = "Customer Choose Service Time", position = 15)
    @NonNull
    private String serviceTime;

    @ApiModelProperty(value = "Customer Problem Description Picture", position = 16)
    private String descriptionPicture;

    @ApiModelProperty(value = "createTime", position = 17)
    private Date creatTime;

    @ApiModelProperty(value = "updateTime", position = 18)
    private Date updateTime;

    @PrePersist
    protected void onCreate(){
        creatTime = new Date();
    }

    @PreUpdate
    protected void onUpdate(){
        updateTime = new Date();
    }
}
