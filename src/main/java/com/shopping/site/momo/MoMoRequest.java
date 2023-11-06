package com.shopping.momo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MoMoRequest {
	private String partnerCode;
    private String orderId;
    private String orderInfo;
    private String accessKey;
    private String amount;
    private String signature;
    private String extraData;
    private String requestId;
    private String notifyUrl;
    private String returnUrl;
    private String requestType;
}
