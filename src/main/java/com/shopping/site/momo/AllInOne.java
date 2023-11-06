//package com.shopping.momo;
//
//import com.mservice.allinone.models.*;
//import com.mservice.allinone.processor.allinone.CaptureMoMo;
//import com.mservice.allinone.processor.allinone.QueryStatusTransaction;
//import com.mservice.shared.sharedmodels.Environment;
//import com.mservice.shared.sharedmodels.Environment.ProcessType;
//public class AllInOne {
//	
//	String partnerCode = "MOMONZ3C20200520";
//	String accessKey = "w8iSLnvJJMuwLkbU";
//	String secretKey = "tUUxP1iaCdHS50OskVmEapOsbxYfaHjT";
//	String apiEndPoint = "https://test-payment.momo.vn/gw_payment/transactionProcessor";
//	
//	String requestId = "1";
//	
//	long amount = 50000;
//	
//	String orderId = "1";
//	
//	String orderInfo = "Pay with MoMo";
//	
//    String returnURL = "https://google.com.vn";
//    String notifyURL = "https://google.com.vn";
//    String requestType =  "captureMoMoWallet";
//    String signaure = "2";
//    
//    Environment environment = Environment.selectEnv("dev", ProcessType.PAY_GATE);
//    
//    CaptureMoMoResponse captureMoMoResponse = CaptureMoMo.process(environment, orderId, requestId, Long.toString(amount), orderInfo, returnURL, notifyURL, "");
//    QueryStatusTransactionResponse queryStatusTransactionResponse = QueryStatusTransaction.process(environment, orderId, requestId);
//    //PayGateResponse payGateResponse = PaymentResult.process(environment,new PayGateResponse());
//    
//}
