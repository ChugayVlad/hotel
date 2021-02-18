package com.my.util;

import com.my.exception.AppException;
import com.my.exception.ServiceException;
import org.apache.log4j.Logger;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordHashing {
    private static final Logger LOG = Logger.getLogger(PasswordHashing.class);

    public static String hash(String password) throws ServiceException {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            LOG.error("Can't hash password", e);
            throw new ServiceException();
        }
        return generatedPassword;
    }
}
