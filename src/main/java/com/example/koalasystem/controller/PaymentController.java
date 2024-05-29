package com.example.koalasystem.controller;

import com.example.koalasystem.dto.PaymentDto;
import com.example.koalasystem.dto.RestResponseDto;
import com.example.koalasystem.entity.Payment;
import com.example.koalasystem.service.PaymentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
@AllArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    RestResponseDto handlePayment( @RequestParam String orderId, @RequestBody @Valid PaymentDto dto) {
        log.info("Receiving payment into koala system");
        Payment payment = paymentService.processPayment(dto.getPaymentType(), dto.getReceivedAmount(), Long.valueOf(orderId));
        return RestResponseDto.builder().message("Accepted payment").data(payment).build();
    }
}
