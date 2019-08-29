package com.accenture.invstate.service;

import com.accenture.invstate.model.context.Invoice;
import com.accenture.invstate.repository.InvoiceAttachmentRepository;
import com.accenture.invstate.repository.InvoicePositionRepository;
import com.accenture.invstate.repository.InvoiceRepository;

import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;
    private final InvoiceAttachmentRepository invoiceAttachmentRepository;
    private final InvoicePositionRepository invoicePositionRepository;

    public InvoiceService(InvoiceRepository invoiceRepository, InvoiceAttachmentRepository invoiceAttachmentRepository,
                          InvoicePositionRepository invoicePositionRepository) {
        this.invoiceRepository = invoiceRepository;
        this.invoiceAttachmentRepository = invoiceAttachmentRepository;
        this.invoicePositionRepository = invoicePositionRepository;
    }

    public void save(Invoice invoice) {

        invoiceRepository.save(invoice);
        System.out.println("");

    }

    public void saveWithAttachments(Invoice invoice) {

        invoicePositionRepository.saveAll(invoice.getInvoicePositions());
        invoiceAttachmentRepository.saveAll(invoice.getInvoiceAttachments());
        invoiceRepository.save(invoice);
        System.out.println("");

    }

    public void saveAll(Set<Invoice> invoices) {

        invoiceRepository.saveAll(invoices);

    }
}
