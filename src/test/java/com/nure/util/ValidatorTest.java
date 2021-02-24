package com.nure.util;

import com.nure.entity.User;
import com.nure.exception.ValidationException;
import org.junit.Test;

import java.sql.Date;

import static org.junit.Assert.*;

public class ValidatorTest {

    @Test
    public void testUser() throws ValidationException {
        User user = new User();
        user.setEmail("test@test.ua");
        user.setFirstName("Test");
        user.setLastName("Test");
        Validator.validateUser(user);
    }
}