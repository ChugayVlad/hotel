package com.my.util;

import com.my.entity.Bill;
import com.my.exception.ServiceException;
import com.my.service.BillService;
import com.my.service.impl.BillServiceImpl;
import org.apache.log4j.Logger;

import java.util.TimerTask;

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
