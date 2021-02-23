package com.nure.util;

import com.nure.entity.Bill;
import com.nure.exception.ServiceException;
import com.nure.service.BillService;
import com.nure.service.impl.BillServiceImpl;
import org.apache.log4j.Logger;

import java.util.TimerTask;

/**
 * Provides a delete logic if {@link Bill} is not paid.
 */
public class DeleteBillJob extends TimerTask {
    private static Logger LOG = Logger.getLogger(DeleteBillJob.class);

    private Bill bill;
    private BillService billService;

    public DeleteBillJob(Bill bill) {
        this.bill = bill;
        billService = new BillServiceImpl();
    }

    @Override
    public void run() {
        LOG.trace("Check is paid bill -->> " + bill);
        try {
            billService.deleteIfNotPaid(bill);
        } catch (ServiceException e) {
            LOG.error("Cannot check payment status", e);
        }
    }
}
