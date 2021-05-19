package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.Offer;
import com.frikiteam.events.domain.model.PaymentMethod;
import com.frikiteam.events.domain.service.PaymentMethodService;
import com.frikiteam.events.resource.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class PaymentMethodsController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get PaymentMethods", description = "Get All PaymentMethods by Pages", tags = {"payment methods"})
    @ApiResponse(
            responseCode = "200",
            description = "All PaymentMethods returned",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/payment-methods")
    public Page<PaymentMethodResource> getAllPaymentMethods(Pageable pageable){
        Page<PaymentMethod> offers = paymentMethodService.getAllPaymentMethods(pageable);
        List<PaymentMethodResource> resources = offers.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get PaymentMethod by id", description = "Get a PaymentMethod given an id", tags = {"payment methods"})
    @ApiResponse(
            responseCode = "200",
            description = "Return a payment method",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/payment-methods/{id}")
    public PaymentMethodResource getByIdPaymentMethod(@PathVariable Long id){
        return convertToResource(paymentMethodService.getPaymentMethodById(id));
    }

    @Operation(summary = "Save a PaymentMethod", description = "Save a PaymentMethod", tags = {"payment methods"})
    @ApiResponse(
            responseCode = "200",
            description = "Return the payment method saved",
            content = @Content(mediaType = "application/json")
    )
    @PostMapping("/payment-methods")
    public PaymentMethodResource savePaymentMethod(@Valid @RequestBody SavePaymentMethodResource resource) {
        PaymentMethod  paymentMethod= convertToEntity(resource);
        return convertToResource(paymentMethodService.savePaymentMethod(paymentMethod));
    }

    @Operation(summary = "Update a PaymentMethod", description = "Update a PaymentMethod given an id and customer body", tags = {"payment methods"})
    @ApiResponse(
            responseCode = "200",
            description = "Return the payment method updated",
            content = @Content(mediaType = "application/json")
    )
    @PutMapping("/payment-methods/{id}")
    public PaymentMethodResource updatePaymentMethod(@PathVariable Long id, @RequestBody SavePaymentMethodResource resource){
        PaymentMethod  paymentMethod = convertToEntity(resource);
        return convertToResource(paymentMethodService.updatePaymentMethod(id, paymentMethod));
    }

    @Operation(summary = "Delete a PaymentMethod", description = "remove a paymentMethod given an id", tags = {"payment methods"})
    @ApiResponse(
            responseCode = "200",
            description = "Return ResponseEntity",
            content = @Content(mediaType = "application/json")
    )
    @DeleteMapping("/payment-methods/{id}")
    public ResponseEntity<?> deletePaymentMethod(@PathVariable Long id){return paymentMethodService.deletePaymentMethod(id); }


    private PaymentMethod convertToEntity(SavePaymentMethodResource resource) {return mapper.map(resource, PaymentMethod.class);
    }
    private PaymentMethodResource convertToResource(PaymentMethod entity) { return mapper.map(entity, PaymentMethodResource.class);
    }
}
