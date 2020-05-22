package com.shopping.momo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MoMoResponse {
	private String requestId;
	private int errorCode;
    private String message;
    private String localMessage;
    private String requestType;
    private String payUrl;
    private String qrCodeUrl;
    private String deeplink;
    private String deeplinkWebInApp; 
    private String signature;
}
