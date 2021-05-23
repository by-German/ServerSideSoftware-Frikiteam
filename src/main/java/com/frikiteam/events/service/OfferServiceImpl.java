package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.Customer;
import com.frikiteam.events.domain.model.Offer;
import com.frikiteam.events.domain.repositories.OfferRepository;
import com.frikiteam.events.domain.service.OfferService;
import com.frikiteam.events.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OfferServiceImpl implements OfferService{
    @Autowired
    private OfferRepository offerRepository;

    @Override
    public Page<Offer> getAllOffers(Pageable pageable) {
        return offerRepository.findAll(pageable);
    }

    @Override
    public Offer getOfferById(Long id) {
        return offerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Offer", "id", id));
    }

    @Override
    public Offer saveOffer(Offer offer) { return offerRepository.save(offer); }

    @Override
    public ResponseEntity<?> deleteOffer(Long id) {
        Offer existed = offerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Offer", "id", id));
        offerRepository.delete(existed);
        return ResponseEntity.ok().build();
    }

    @Override
    public Offer updateOffer(Long id, Offer offer) {
        Offer existed = offerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Offer", "id", id));

        existed.setDiscountAmount(offer.getDiscountAmount());
        existed.setName(offer.getName());

        return offerRepository.save(existed);
    }

}
