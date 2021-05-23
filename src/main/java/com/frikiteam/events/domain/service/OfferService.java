package com.frikiteam.events.domain.service;

import com.frikiteam.events.domain.model.Customer;
import com.frikiteam.events.domain.model.Offer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface OfferService {
    Page<Offer> getAllOffers(Pageable pageable);
    Offer getOfferById(Long id);
    Offer saveOffer(Offer offer);
    ResponseEntity<?> deleteOffer(Long id);
    Offer updateOffer(Long id, Offer offer);
}
