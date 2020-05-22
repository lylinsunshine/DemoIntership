package com.shopping.clientcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shopping.dto.ProductDetailDTO;
import com.shopping.momo.MoMoPayment;
import com.shopping.momo.MoMoRequest;
import com.shopping.momo.MoMoResponse;
import com.shopping.service.IProductService;
import com.shopping.util.ResponseModel;

@CrossOrigin
@RestController
@RequestMapping("/clients/payment")
public class ClientPaymentController {
	
	@GetMapping()
	public ResponseEntity<MoMoResponse> getProductInfo() {
		MoMoPayment p = new MoMoPayment();
		System.out.println(p.payment());
		return p.payment();
	}
}
