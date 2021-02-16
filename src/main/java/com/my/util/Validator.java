package com.my.util;

import com.my.exception.AppException;
import com.my.exception.ServiceException;

import java.sql.Date;
import java.time.LocalDate;

public class Validator {
    public static void validateDate(Date dateIn, Date dateOut) throws ServiceException {
        LocalDate currentDate = LocalDate.now();
        if (dateIn.compareTo(Date.valueOf(currentDate)) < 0 || dateOut.compareTo(Date.valueOf(currentDate)) < 0){
            throw new ServiceException("Date cannot be past!");
        }
        if (dateIn.compareTo(dateOut) > 0) {
            throw new ServiceException("Arrival date cannot be less than departure date");
        }
        if (dateIn.equals(dateOut)){
            throw new ServiceException("You can't choose the same date");
        }
    }
}
