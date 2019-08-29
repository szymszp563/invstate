package com.accenture.invstate.model;

import com.accenture.invstate.model.context.Invoice;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
public class InvoicePosition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String unitPrice;
    private String approvedUnitPrice;
    private boolean approved;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;

    @Override
    public String toString() {
        return "InvoicePosition{"
                + "id=" + id
                + ", unitPrice='" + unitPrice + '\''
                + ", approvedUnitPrice='" + approvedUnitPrice + '\''
                + ", approved=" + approved
                + ", quantity=" + quantity
                + '}';
    }

}
