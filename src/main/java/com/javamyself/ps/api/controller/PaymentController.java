package com.javamyself.ps.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javamyself.ps.api.entity.Payment;
import com.javamyself.ps.api.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService paymentService;

	@PostMapping("/doPayment")
	public Payment doPayment(@RequestBody Payment payment) throws JsonProcessingException {

		return paymentService.doPayment(payment);
	}

	@GetMapping("/{orderId}")
	public Payment finPaymentHistoryByOrderId(@PathVariable int orderId) throws JsonProcessingException {
		return paymentService.finPaymentHistoryByOrderId(orderId);
	}
}
