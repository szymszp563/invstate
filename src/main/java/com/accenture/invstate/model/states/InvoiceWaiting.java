package com.accenture.invstate.model.states;

import com.accenture.invstate.model.InvoicePosition;
import com.accenture.invstate.model.context.Invoice;
import com.accenture.invstate.model.context.InvoiceState;
import com.accenture.invstate.model.context.State;

import java.util.Set;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class InvoiceWaiting extends InvoiceState {

    private boolean approved;

    @Override
    public void changeInvoiceState(Invoice context) {
        if (approved) {
            context.setState(State.APPROVED);

        } else {
            context.setState(State.CREATED);

        }
    }

    @Override
    public void modify(Invoice context) {
        this.approved = false;

        changeInvoiceState(context);

        String currState = context.getCurrent().getState().toString();

        log.debug("Request for change, changed state! Current state: "
                + currState.substring(currState.lastIndexOf(".") + 1, currState.lastIndexOf("@")));

    }

    @Override
    public void complete(Invoice context) {
        String currState = context.getCurrent().getState().toString();

        log.debug("Forbidden in your current state! Current state: "
                + currState.substring(currState.lastIndexOf(".") + 1, currState.lastIndexOf("@")));
    }

    @Override
    public void approve(Invoice context) {
        context.getInvoicePositions().forEach(p -> p.setApproved(true));
        setApprovedPrices(context.getInvoicePositions());
        this.approved = true;

        changeInvoiceState(context);

        String currState = context.getCurrent().getState().toString();

        log.debug("Approved, changed state! Current state: "
                + currState.substring(currState.lastIndexOf(".") + 1, currState.lastIndexOf("@")));
    }

    @Override
    public void reject(Invoice context) {

        setApprovedPrices(context.getInvoicePositions());
        this.approved = false;

        changeInvoiceState(context);

        String currState = context.getCurrent().getState().toString();

        log.debug("Rejected, changed state! Current state: "
                + currState.substring(currState.lastIndexOf(".") + 1, currState.lastIndexOf("@")));
    }

    public void setApprovedPrices(Set<InvoicePosition> positions) {
        positions.forEach(p -> {
            if (p.isApproved()) {
                p.setApprovedUnitPrice(p.getUnitPrice());
            }
        });
    }

}
