package com.accenture.invstate.model.states;

import com.accenture.invstate.model.context.Invoice;
import com.accenture.invstate.model.context.InvoiceState;
import com.accenture.invstate.model.context.State;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class InvoiceInTransit extends InvoiceState {

    private boolean trackingNoCleared;

    @Override
    public void changeInvoiceState(Invoice context) {
        if (trackingNoCleared) {
            context.setState(State.APPROVED);
        } else {
            context.setState(State.COMPLETED);
        }

    }

    @Override
    public void modify(Invoice context) {

        context.setTrackingNo(null);
        trackingNoCleared = true;

        changeInvoiceState(context);

        String currState = context.getCurrent().getState().toString();
        log.debug("Modified invoice and erased TrackingNo , changed state! Current state: "
                + currState.substring(currState.lastIndexOf(".") + 1, currState.lastIndexOf("@")));
    }

    @Override
    public void complete(Invoice context) {

        trackingNoCleared = false;

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
