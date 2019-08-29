package com.accenture.invstate.model.context;

import com.accenture.invstate.model.states.InvoiceApproved;
import com.accenture.invstate.model.states.InvoiceCompleted;
import com.accenture.invstate.model.states.InvoiceCreated;
import com.accenture.invstate.model.states.InvoiceInTransit;
import com.accenture.invstate.model.states.InvoiceWaiting;

public enum State {

    CREATED, WAITING, APPROVED, COMPLETED, TRANSIT;

    public InvoiceState getState() {
        switch (this) {
            case CREATED:
                return new InvoiceCreated();
            case WAITING:
                return new InvoiceWaiting();
            case APPROVED:
                return new InvoiceApproved();
            case COMPLETED:
                return new InvoiceCompleted();
            case TRANSIT:
                return new InvoiceInTransit();
            default:
                System.out.println("WRONG");
                return new InvoiceCreated();
        }
    }
}
