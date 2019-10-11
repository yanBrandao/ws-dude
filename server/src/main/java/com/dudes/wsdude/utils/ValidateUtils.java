package utils;

import exception.NotFoundException;

public class ValidateUtils {
    private static  final String VALID_STRING_REGEX = "[a-zA-Zà-ùÀ-Ù0-9s-]+[,._a-zA-Zà-ùÀ-Ù0-9\\s-]*";

    public static void checkFound(Object obj, String message, String... msgArgs) {
        if (obj == null) {
            throw new NotFoundException(message, msgArgs);
        }
    }

}
