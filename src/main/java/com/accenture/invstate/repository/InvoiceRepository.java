package com.accenture.invstate.repository;

import com.accenture.invstate.model.context.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
