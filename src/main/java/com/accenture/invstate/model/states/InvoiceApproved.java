package com.accenture.invstate.model.states;

import com.accenture.invstate.model.context.Invoice;
import com.accenture.invstate.model.context.InvoiceState;
import com.accenture.invstate.model.context.State;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class InvoiceApproved extends InvoiceState {

    private boolean requestForChange;
    private boolean transitNumberExists;

    @Override
    public void changeInvoiceState(Invoice context) {
        if (requestForChange) {
            context.setState(State.CREATED);
        } else if (transitNumberExists) {
            context.setState(State.TRANSIT);
        } else {
            context.setState(State.COMPLETED);
        }

    }

    @Override
    public void modify(Invoice context) {
        this.requestForChange = true;

        changeInvoiceState(context);

        String currState = context.getCurrent().getState().toString();
        log.debug("Modified approved invoice (request for change), changed state! Current state: "
                + currState.substring(currState.lastIndexOf(".") + 1, currState.lastIndexOf("@")));
    }

    @Override
    public void complete(Invoice context) {
        this.requestForChange = false;
        if (context.getTrackingNo() != null) {
            this.transitNumberExists = true;
        } else {
            transitNumberExists = false;
        }

        changeInvoiceState(context);

        String currState = context.getCurrent().getState().toString();
        log.debug("Completed, changed state! Current state: "
                + currState.substring(currState.lastIndexOf(".") + 1, currState.lastIndexOf("@")));
    }

    @Override
    public void approve(Invoice context) {
        String currState = context.getCurrent().getState().toString();
        log.debug("Forbidden in your current state! Current state: "
                + currState.substring(currState.lastIndexOf(".") + 1, currState.lastIndexOf("@")));
    }

    @Override
    public void reject(Invoice context) {
        String currState = context.getCurrent().getState().toString();
        log.debug("Forbidden in your current state! Current state: "
                + currState.substring(currState.lastIndexOf(".") + 1, currState.lastIndexOf("@")));
    }


}
