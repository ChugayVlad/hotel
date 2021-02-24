package com.nure.util;

import com.nure.entity.Bill;
import com.nure.entity.User;
import com.nure.exception.ValidationException;

import java.sql.Date;
import java.time.LocalDate;
import java.util.regex.Pattern;

/**
 * Provides a validation logic of entities.
 */
public class Validator {
    public static void validateDate(Date dateIn, Date dateOut) throws ValidationException {
        LocalDate currentDate = LocalDate.now();
        if (dateIn.compareTo(Date.valueOf(currentDate)) < 0 || dateOut.compareTo(Date.valueOf(currentDate)) < 0){
            throw new ValidationException("exception.past_date");
        }
        if (dateIn.compareTo(dateOut) > 0) {
            throw new ValidationException("exception.less_date");
        }
        if (dateIn.equals(dateOut)){
            throw new ValidationException("exception.same_date");
        }
    }

    public static void validateUser(User user) throws ValidationException {
        Pattern pattern = Pattern.compile("^[A-ZА-ЯЁЇЄ][a-zа-яєїё]+");
        if (!pattern.matcher(user.getFirstName()).matches()){
            throw new ValidationException("Name does not match pattern");
        }
        if (!pattern.matcher(user.getLastName()).matches()){
            throw new ValidationException("Last name does not match pattern");
        }
        pattern = Pattern.compile("^[a-zA-Z0-9_!#$%&’*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$");
        if (!pattern.matcher(user.getEmail()).matches()){
            throw new ValidationException("Email does not match pattern");
        }
    }
}
