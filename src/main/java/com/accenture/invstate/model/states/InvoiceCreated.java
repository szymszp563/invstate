package com.accenture.invstate.model.states;

import com.accenture.invstate.model.InvoiceAttachment;
import com.accenture.invstate.model.InvoicePosition;
import com.accenture.invstate.model.context.Invoice;
import com.accenture.invstate.model.context.InvoiceState;
import com.accenture.invstate.model.context.State;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class InvoiceCreated extends InvoiceState {

    @Override
    public void changeInvoiceState(Invoice context) {
        context.setState(State.WAITING);
    }

    @Override
    public void modify(Invoice context) {

        context.addPosition(InvoicePosition.builder().unitPrice("10 $").quantity(100).approved(false).build());
        context.addPosition(InvoicePosition.builder().unitPrice("66 $").quantity(19).approved(false).build());

        context.addAttachment(InvoiceAttachment.builder().type("doc").url("www.hello.com").build());
        context.addAttachment(InvoiceAttachment.builder().type("img").url("www.image.org").build());

        changeInvoiceState(context);

        String currState = context.getCurrent().getState().toString();
        log.debug("Modified invoice, changed state! Current state: "
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
