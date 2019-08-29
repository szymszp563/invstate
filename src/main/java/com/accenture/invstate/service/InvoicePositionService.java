package com.accenture.invstate.service;

import com.accenture.invstate.model.InvoicePosition;
import com.accenture.invstate.repository.InvoicePositionRepository;

import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class InvoicePositionService {

    private final InvoicePositionRepository invoicePositionRepository;

    public InvoicePositionService(InvoicePositionRepository invoicePositionRepository) {
        this.invoicePositionRepository = invoicePositionRepository;
    }

    public void save(InvoicePosition invoicePosition) {

        invoicePositionRepository.save(invoicePosition);
    }

    public void saveAll(Set<InvoicePosition> invoicePositions) {

        invoicePositionRepository.saveAll(invoicePositions);
    }
}
