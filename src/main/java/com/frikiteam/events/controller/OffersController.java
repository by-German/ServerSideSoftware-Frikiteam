package com.frikiteam.events.controller;

import com.frikiteam.events.domain.model.Customer;
import com.frikiteam.events.domain.model.Offer;
import com.frikiteam.events.domain.service.CustomerService;
import com.frikiteam.events.domain.service.OfferService;
import com.frikiteam.events.resource.CustomerResource;
import com.frikiteam.events.resource.OfferResource;
import com.frikiteam.events.resource.SaveCustomerResource;
import com.frikiteam.events.resource.SaveOfferResource;
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
public class OffersController {
    @Autowired
    private OfferService offerService;

    @Autowired
    private ModelMapper mapper;

    @Operation(summary = "Get Offers", description = "Get All Offers by Pages", tags = {"offers"})
    @ApiResponse(
            responseCode = "200",
            description = "All Offers returned",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/offers")
    public Page<OfferResource> getAllOffers(Pageable pageable){
        Page<Offer> offers = offerService.getAllOffers(pageable);
        List<OfferResource> resources = offers.getContent()
                .stream()
                .map(this::convertToResource)
                .collect(Collectors.toList());
        return new PageImpl<>(resources, pageable, resources.size());
    }

    @Operation(summary = "Get Offer by id", description = "Get a Offer given an id", tags = {"offers"})
    @ApiResponse(
            responseCode = "200",
            description = "Return a offer",
            content = @Content(mediaType = "application/json")
    )
    @GetMapping("/offers/{id}")
    public OfferResource getByIdOffer(@PathVariable Long id){
        return convertToResource(offerService.getOfferById(id));
    }

    @Operation(summary = "Save a Offer   ", description = "Save a Offer", tags = {"offers"})
    @ApiResponse(
            responseCode = "200",
            description = "Return the offer saved",
            content = @Content(mediaType = "application/json")
    )
    @PostMapping("/offers")
    public OfferResource saveOffer(@Valid @RequestBody SaveOfferResource resource) {
        Offer offer= convertToEntity(resource);
        return convertToResource(offerService.saveOffer(offer));
    }

    @Operation(summary = "Update a Offer", description = "Update a offer given an id and customer body", tags = {"offers"})
    @ApiResponse(
            responseCode = "200",
            description = "Return the offer updated",
            content = @Content(mediaType = "application/json")
    )
    @PutMapping("/offers/{id}")
    public OfferResource updateOffer(@PathVariable Long id, @RequestBody SaveOfferResource resource){
        Offer offer = convertToEntity(resource);
        return convertToResource(offerService.updateOffer(id, offer));
    }

    @Operation(summary = "Delete a Offer", description = "remove a offer given an id", tags = {"offers"})
    @ApiResponse(
            responseCode = "200",
            description = "Return ResponseEntity",
            content = @Content(mediaType = "application/json")
    )
    @DeleteMapping("/offers/{id}")
    public ResponseEntity<?> deleteOffer(@PathVariable Long id){return offerService.deleteOffer(id); }


    private Offer convertToEntity(SaveOfferResource resource) {return mapper.map(resource, Offer.class);
    }
    private OfferResource convertToResource(Offer entity) {
        return mapper.map(entity, OfferResource.class);
    }
}
