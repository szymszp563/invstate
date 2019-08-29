package com.accenture.invstate.model.context;

import com.accenture.invstate.model.Auditable;
import com.accenture.invstate.model.InvoiceAttachment;
import com.accenture.invstate.model.InvoicePosition;
import com.accenture.invstate.utils.InvoiceEntityListener;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@EntityListeners(InvoiceEntityListener.class)
public class Invoice extends Auditable<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(value = EnumType.STRING)
    private State current = State.CREATED;

    private String invoiceNo;
    private String trackingNo;

    @OneToMany(mappedBy = "invoice")
    private Set<InvoiceAttachment> invoiceAttachments = new HashSet<>();

    @OneToMany(mappedBy = "invoice")
    private Set<InvoicePosition> invoicePositions = new HashSet<>();

    public void setState(State state) {
        current = state;
    }

    public void requestForApproval() {
        current.getState().modify(this);
    }

    public void completeDocuments() {
        current.getState().complete(this);
    }

    public String finalApprove(String comment) {
        current.getState().approve(this);
        return comment;
    }

    public String finalReject(String comment) {
        current.getState().reject(this);
        return comment;
    }

    public void addAttachment(InvoiceAttachment attachment) {
        this.invoiceAttachments.add(attachment);
        attachment.setInvoice(this);
    }

    public void addPosition(InvoicePosition position) {
        this.invoicePositions.add(position);
        position.setInvoice(this);
    }

    @Override
    public String toString() {
        return "Invoice{"
                + "id=" + id
                + ", current=" + current
                + ", invoiceNo='" + invoiceNo + '\''
                + ", trackingNo='" + trackingNo + '\''
                + ", invoiceAttachments=" + invoiceAttachments
                + ", invoicePositions=" + invoicePositions
                + '}';
    }

}
