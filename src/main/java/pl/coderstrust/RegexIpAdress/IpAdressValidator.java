package pl.coderstrust.RegexIpAdress;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpAdressValidator {

    private static final String REGEXIP = "^((\\d\\d?+)\\.|(1\\d\\d)\\.|(2[0-4]\\d)\\.|(25[0-5])\\.){3}((\\d\\d?+)|(1\\d\\d)|(2[0-4]\\d)|(25[0-5]))$";

    private static final Pattern PATTERN = Pattern.compile(REGEXIP);

    public static boolean isIpAddress(String ipAdress) {
        if (ipAdress == null) {
            throw new IllegalArgumentException("String cannot be null");
        }
        Matcher matcher = PATTERN.matcher(ipAdress);
        return matcher.matches();
    }
}
