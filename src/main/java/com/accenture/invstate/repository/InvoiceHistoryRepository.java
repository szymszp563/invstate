package com.accenture.invstate.repository;

import com.accenture.invstate.model.InvoiceHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceHistoryRepository extends JpaRepository<InvoiceHistory, Long> {
}
