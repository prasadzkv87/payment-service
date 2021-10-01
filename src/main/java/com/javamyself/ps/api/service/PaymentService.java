package com.javamyself.ps.api.service;

import java.util.Random;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javamyself.ps.api.entity.Payment;
import com.javamyself.ps.api.repository.PaymentRepository;

@Service
public class PaymentService {

	private Logger log = LoggerFactory.getLogger(PaymentService.class);

	@Autowired
	private PaymentRepository paymentRepository;

	public Payment doPayment(Payment payment) throws JsonProcessingException {

		payment.setPaymentStatus(paymentProcessing());
		payment.setTransactionId(UUID.randomUUID().toString());
		log.info("PaymentService Request : {}", new ObjectMapper().writeValueAsString(payment));
		return paymentRepository.save(payment);
	}

	public String paymentProcessing() {

		// api call should be 3rd party payment gateway(paypal, paytm..)
		return new Random().nextBoolean() ? "sucess" : "failure";
	}

	public Payment finPaymentHistoryByOrderId(int orderId) throws JsonProcessingException {
		Payment payment = paymentRepository.findByOrderId(orderId);
		log.info("PaymentService finPaymentHistoryByOrderId : {}", new ObjectMapper().writeValueAsString(payment));
		return payment;
	}
}
