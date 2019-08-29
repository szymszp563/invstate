package com.accenture.invstate.controller;

import com.accenture.invstate.model.context.Invoice;
import com.accenture.invstate.model.states.InvoiceApproved;
import com.accenture.invstate.model.states.InvoiceCompleted;
import com.accenture.invstate.model.states.InvoiceCreated;
import com.accenture.invstate.model.states.InvoiceInTransit;
import com.accenture.invstate.service.InvoiceService;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class InvoiceTestController {

    private Invoice testInvoice;
    private final InvoiceService invoiceService;

    public InvoiceTestController(InvoiceService invoiceService) {
        this.invoiceService = invoiceService;
    }

    @GetMapping("/create")
    public String createInvoice() {

        String currState;

        testInvoice = new Invoice();
        testInvoice.setInvoiceNo("1");
        currState = testInvoice.getCurrent().getState().toString();
        currState = currState.substring(currState.lastIndexOf(".") + 1, currState.lastIndexOf("@"));

        invoiceService.save(testInvoice);

        log.debug("Invoice created! Current state: " + currState);
        return "Invoice created and it's state is: " + currState;
    }

    @GetMapping("/modify")
    public String modifyInvoice() {
        if (testInvoice == null) {
            log.debug("Invoice is null");
            return "Invoice is null";
        }

        testInvoice.requestForApproval();
        invoiceService.saveWithAttachments(testInvoice);

        String currState = testInvoice.getCurrent().getState().toString();
        currState = currState.substring(currState.lastIndexOf(".") + 1, currState.lastIndexOf("@"));
        if (testInvoice.getCurrent().getState() instanceof InvoiceCreated) {
            return "Invoice modified and it's state is: " + currState;
        } else if (testInvoice.getCurrent().getState() instanceof InvoiceApproved) {
            return "Invoice trackingNo deleted: " + currState;
        }

        return "Forbidden action in state: " + currState;

    }

    @GetMapping("/approve")
    public String approveInvoice() {
        if (testInvoice == null) {
            log.debug("Invoice is null");
            return "Invoice is null";
        }

        String comment = testInvoice.finalApprove("well pricing. Approved!");


        String currState = testInvoice.getCurrent().getState().toString();
        currState = currState.substring(currState.lastIndexOf(".") + 1, currState.lastIndexOf("@"));
        if (testInvoice.getCurrent().getState() instanceof InvoiceApproved) {
            invoiceService.saveWithAttachments(testInvoice);
            return "Invoice approved and it's state is: "
                    + currState
                    + "\nWith comment: " + comment;
        }

        return "Forbidden action in state: " + currState;

    }

    @GetMapping("/reject")
    public String rejectInvoice() {

        if (testInvoice == null) {
            log.debug("Invoice is null");
            return "Invoice is null";
        }

        String comment = testInvoice.finalReject("Nonono. Not Approved!");


        String currState = testInvoice.getCurrent().getState().toString();
        currState = currState.substring(currState.lastIndexOf(".") + 1, currState.lastIndexOf("@"));
        if (testInvoice.getCurrent().getState() instanceof InvoiceCreated) {
            invoiceService.saveWithAttachments(testInvoice);
            return "Invoice rejected and it's state is: "
                    + currState
                    + "\nWith comment: " + comment;
        }

        return "Forbidden action in state: " + currState;

    }

    @GetMapping("/complete")
    public String completeInvoice() {
        if (testInvoice == null) {
            log.debug("Invoice is null");
            return "Invoice is null";
        }

        testInvoice.completeDocuments();
        invoiceService.save(testInvoice);

        String currState = testInvoice.getCurrent().getState().toString();
        currState = currState.substring(currState.lastIndexOf(".") + 1, currState.lastIndexOf("@"));
        if (testInvoice.getCurrent().getState() instanceof InvoiceCompleted) {
            return "Invoice completed and it's state is: "
                    + currState;
        } else if (testInvoice.getCurrent().getState() instanceof InvoiceInTransit) {
            return "Invoice in transit and it's state is: "
                    + currState
                    + "\nmodify to erase tracking no or complete to end invoice ";
        }

        return "Forbidden action in state: " + currState;

    }

    @GetMapping("/setTrack")
    public String setNo() {
        if (testInvoice == null) {
            log.debug("Invoice is null");
            return "Invoice is null";
        }

        testInvoice.setTrackingNo("50");
        invoiceService.save(testInvoice);

        return "Invoice Tracking No now is: " + testInvoice.getTrackingNo();
    }

    @GetMapping("/getTrack")
    public String getNo() {

        if (testInvoice != null) {
            return "Getting Tracking No: " + testInvoice.getTrackingNo();
        }

        return "empty Invoice";
    }

    @GetMapping("/get")
    public Object getInvoice() {

        if (testInvoice == null) {
            return "Invoice is null";
        }

        return testInvoice.toString();
    }
}
