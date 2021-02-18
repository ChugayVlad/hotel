package com.my.util;

import com.my.entity.User;
import com.my.exception.AppException;
import com.my.exception.ServiceException;
import com.my.exception.ValidationException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.regex.Pattern;

public class Validator {
    public static void validateDate(Date dateIn, Date dateOut) throws ValidationException {
        LocalDate currentDate = LocalDate.now();
        if (dateIn.compareTo(Date.valueOf(currentDate)) < 0 || dateOut.compareTo(Date.valueOf(currentDate)) < 0){
            throw new ValidationException("Date cannot be past!");
        }
        if (dateIn.compareTo(dateOut) > 0) {
            throw new ValidationException("Arrival date cannot be less than departure date");
        }
        if (dateIn.equals(dateOut)){
            throw new ValidationException("You can't choose the same date");
        }
    }

    public static void validateUser(User user) throws ValidationException {
        Pattern pattern = Pattern.compile("^[A-ZА-ЯЁЇЄ][a-zа-яєїё]+");
        if (!pattern.matcher(user.getFirstName()).matches()){
            throw new ValidationException("Name does not match");
        }
    }
}
