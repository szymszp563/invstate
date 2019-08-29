package com.accenture.invstate.model;

import static javax.persistence.TemporalType.TIMESTAMP;

import com.accenture.invstate.model.context.Invoice;
import com.accenture.invstate.model.context.State;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@Setter
@NoArgsConstructor
@Entity
@EntityListeners(AuditingEntityListener.class)
public class InvoiceHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "invoice_id", foreignKey = @ForeignKey(name = "FK_invoice_history"))
    private Invoice invoice;

    @Enumerated(value = EnumType.STRING)
    private State current = State.CREATED;

    private String invoiceNo;
    private String trackingNo;

    @Lob
    private String invoiceAttachmentsInfo;
    @Lob
    private String invoicePositionsInfo;

    @CreatedBy
    private String modifiedBy;

    @CreatedDate
    @Temporal(TIMESTAMP)
    private Date modifiedDate;

    public InvoiceHistory(Invoice invoice) {
        this.invoice = invoice;
        this.current = invoice.getCurrent();
        this.invoiceNo = invoice.getInvoiceNo();
        this.trackingNo = invoice.getTrackingNo();
        StringBuilder s1 = new StringBuilder();
        invoice.getInvoiceAttachments().forEach(a -> s1.append(a.getId()).append(" "));
        this.invoiceAttachmentsInfo = s1.toString();
        StringBuilder s2 = new StringBuilder();
        invoice.getInvoicePositions().forEach(a -> s2.append(a.getId()).append(" "));
        this.invoicePositionsInfo = s2.toString();
    }

}
