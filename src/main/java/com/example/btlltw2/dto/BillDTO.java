package com.example.btlltw2.dto;

import com.example.btlltw2.entity.BillStatus;
import lombok.Builder;
import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
@Builder
public class BillDTO {
    private Long id;

    private Date paymentTime;
    private String address;
    private String phone;
    private String nameReceiver;
    private BillStatus status;
    private String paymentCode;
    private List<BillItemDTO> items;
    private Long userId;
    private Long cartId;

}
