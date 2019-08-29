package com.accenture.invstate.model.context;

public abstract class InvoiceState {

    public abstract void changeInvoiceState(Invoice context);

    public abstract void modify(Invoice context);

    public abstract void complete(Invoice context);

    public abstract void approve(Invoice context);

    public abstract void reject(Invoice context);
}
