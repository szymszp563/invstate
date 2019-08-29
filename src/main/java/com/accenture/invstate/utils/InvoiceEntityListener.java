package com.accenture.invstate.utils;

import com.accenture.invstate.model.InvoiceHistory;
import com.accenture.invstate.model.context.Invoice;
import com.accenture.invstate.repository.InvoiceHistoryRepository;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

public class InvoiceEntityListener {

    @PrePersist
    public void prePersist(Invoice target) {
        perform(target);
    }

    @PreUpdate
    public void preUpdate(Invoice target) {
        perform(target);
    }

    @PreRemove
    public void preRemove(Invoice target) {
        perform(target);
    }

    @Transactional(Transactional.TxType.MANDATORY)
    void perform(Invoice target) {
        InvoiceHistoryRepository invoiceHistoryRepository = BeanUtil.getBean(InvoiceHistoryRepository.class);
        invoiceHistoryRepository.save(new InvoiceHistory(target));
    }
}
