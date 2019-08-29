package com.accenture.invstate.repository;

import com.accenture.invstate.model.InvoiceAttachment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceAttachmentRepository extends JpaRepository<InvoiceAttachment, Long> {
}
