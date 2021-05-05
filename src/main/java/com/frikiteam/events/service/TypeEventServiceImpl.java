package com.frikiteam.events.service;

import com.frikiteam.events.domain.model.TypeEvent;
import com.frikiteam.events.domain.repositories.TypeEventRepository;
import com.frikiteam.events.domain.service.TypeEventService;
import com.frikiteam.events.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TypeEventServiceImpl implements TypeEventService {
    @Autowired
    private TypeEventRepository typeEventRepository;


    @Override
    public Page<TypeEvent> getAllTypeEvents(Pageable pageable) {
        return typeEventRepository.findAll(pageable);
    }


    @Override
    public TypeEvent getTypeEventById(Long TypeEventId) {
        return typeEventRepository.findById(TypeEventId)
                .orElseThrow(() -> new ResourceNotFoundException("TypeEvent", "Id", TypeEventId));
    }

    @Override
    public TypeEvent createTypeEvent(TypeEvent typeEvent) {
        return typeEventRepository.save(typeEvent);
    }

    @Override
    public TypeEvent updateTypeEvent(Long typeEventId, TypeEvent typeEventDetails) {
        return typeEventRepository.findById(typeEventId)
                .map(typeEvent -> {
                    typeEvent.setName(typeEventDetails.getName());
                    return typeEventRepository.save(typeEvent); })
                .orElseThrow(() -> new ResourceNotFoundException("TypeEvent", "Id", typeEventId));
    }

    @Override
    public ResponseEntity<?> deleteTypeEvent(Long TypeEventId) {
        return typeEventRepository.findById(TypeEventId)
                .map(TypeEvent -> {
                    typeEventRepository.delete(TypeEvent);
                    return ResponseEntity.ok().build(); })
                .orElseThrow(() -> new ResourceNotFoundException("TypeEvent", "Id", TypeEventId));

    }
}
