package com.accenture.invstate.service;

import com.accenture.invstate.model.InvoiceAttachment;
import com.accenture.invstate.repository.InvoiceAttachmentRepository;

import java.util.Set;

import org.springframework.stereotype.Service;

@Service
public class InvoiceAttachmentService {

    private final InvoiceAttachmentRepository invoiceAttachmentRepository;

    public InvoiceAttachmentService(InvoiceAttachmentRepository invoiceAttachmentRepository) {
        this.invoiceAttachmentRepository = invoiceAttachmentRepository;
    }

    public void save(InvoiceAttachment invoiceAttachment) {

        invoiceAttachmentRepository.save(invoiceAttachment);

    }

    public void saveAll(Set<InvoiceAttachment> invoiceAttachments) {

        invoiceAttachmentRepository.saveAll(invoiceAttachments);
    }
}
