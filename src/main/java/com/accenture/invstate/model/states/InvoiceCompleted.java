package com.accenture.invstate.model.states;

import com.accenture.invstate.model.context.Invoice;
import com.accenture.invstate.model.context.InvoiceState;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class InvoiceCompleted extends InvoiceState {
    @Override
    public void changeInvoiceState(Invoice context) {

    }

    @Override
    public void modify(Invoice context) {
        String currState = context.getCurrent().getState().toString();
        log.debug("Forbidden in your current state! Current state: "
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
