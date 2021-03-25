package ceng.estu.utilities;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * @author reuzun
 */
public class Validator {
    public static boolean isValidEmailAddress(String email) {
        return EmailValidator.getInstance().isValid(email);
    }
}
