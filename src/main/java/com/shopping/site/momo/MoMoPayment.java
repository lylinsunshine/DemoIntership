package com.shopping.site.momo;

import com.shopping.site.util.Encoder;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MoMoPayment {
	String partnerCode = "MOMONZ3C20200520";
	String accessKey = "w8iSLnvJJMuwLkbU";
	String secretKey = "tUUxP1iaCdHS50OskVmEapOsbxYfaHjT";
	String apiEndPoint = "https://test-payment.momo.vn/gw_payment/transactionProcessor";

	String requestId = "0";

	long amount = 50000;

	String orderId = "-1";

	String orderInfo = "Pay with MoMo";

	String returnUrl = "http://localhost:4200/client/cart";
	String notifyUrl = "http://localhost:4200/client/cart";
	String requestType = "captureMoMoWallet";
	String extraData = "";
	String rawData = 
			"partnerCode=" + partnerCode + 
			"&accessKey=" + accessKey + 
			"&requestId=" + requestId + 
			"&amount="+ Long.toString(amount) + 
			"&orderId=" + orderId + 
			"&orderInfo=" + orderInfo +
			"&returnUrl=" + returnUrl +
			"&notifyUrl=" + notifyUrl +
			"&extraData=" + extraData;
	Encoder encoder = new Encoder();
	String signature = encoder.signHmacSHA256(rawData, secretKey);

	String url = "https://test-payment.momo.vn/gw_payment/transactionProcessor";
	
	public ResponseEntity<MoMoResponse> payment () {
		RestTemplate restTemplate = new RestTemplate();
		MoMoRequest request = new MoMoRequest(partnerCode, orderId, orderInfo, accessKey, Long.toString(amount), signature, extraData, requestId, notifyUrl, returnUrl, requestType);
		
		HttpEntity<MoMoRequest> requestBody = new HttpEntity<MoMoRequest>(request);
		ResponseEntity<MoMoResponse> result = restTemplate.postForEntity(url, requestBody, MoMoResponse.class);
		return result;
	}

	
}
