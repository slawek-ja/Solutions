package pl.coderstrust.utils;

public class ParameterValidator {
    public static void validateStringParameter(String paramValue, String paramName) {
        if (paramValue == null) {
            throw new IllegalArgumentException(String.format("%s cannot be null", paramName));
        }
        if (paramValue.isEmpty()) {
            throw new IllegalArgumentException(String.format("%s cannot be empty", paramName));
        }
    }
}
