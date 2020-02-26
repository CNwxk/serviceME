package com.team6.serviceme.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.util.Date;

/**
 * Set Bid Attributes
 *
 */
@Data   //Auto add Getter and Setter
@Entity
@ApiModel(description="Bid Entity")
@NoArgsConstructor
public class Bid {
    @ApiModelProperty(value = "Id", position = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    @ApiModelProperty(value = "Order Id", position = 2)
    private Long orderId;

    @ApiModelProperty(value = "User Id", position = 3)
    private Long userId;

    @ApiModelProperty(value = "Vendor Bid Price", position = 4)
    @NonNull
    private String bidPrice;

    @ApiModelProperty(value = "createTime", position = 5)
    private Date creatTime;

    @ApiModelProperty(value = "updateTime", position = 6)
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

