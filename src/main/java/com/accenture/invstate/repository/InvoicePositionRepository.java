package com.accenture.invstate.repository;

import com.accenture.invstate.model.InvoicePosition;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoicePositionRepository extends JpaRepository<InvoicePosition, Long> {
}
