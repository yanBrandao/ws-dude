package com.dudes.wsdude.utils;

import com.dudes.wsdude.exception.InvalidValueException;
import com.dudes.wsdude.exception.NotFoundException;

public class ValidateUtils {
    private static  final String VALID_STRING_REGEX = "[a-zA-Zà-ùÀ-Ù0-9s-]+[,._a-zA-Zà-ùÀ-Ù0-9\\s-]*";

    public static void checkFound(Object obj, String message, String... msgArgs) {
        if (obj == null) {
            throw new NotFoundException(message, msgArgs);
        }
    }

    public static void checkBiggerThanZero(Long number, String message, String... msgArgs) {
        if (!isBiggerThanZero(number)) {
            throw new InvalidValueException(message, msgArgs);
        }
    }

    public static void checkBiggerThanZero(Integer number, String message, String... msgArgs) {
        Long lng = (number == null ? null : number.longValue());
        checkBiggerThanZero(lng, message, msgArgs);
    }

    public static void checkMustBeNullOrZero(Long number, String message, String... msgArgs) {
        if (number != null && number > 0) {
            throw new InvalidValueException(message, msgArgs);
        }
    }

    private static boolean isBiggerThanZero(Long number) {
        return number != null && number > 0;
    }
}
