package pl.coderstrust.ipadrress;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IpAddressValidator {

    private static final String REGEXIP = "^((\\d\\d?+)\\.|(1\\d\\d)\\.|(2[0-4]\\d)\\.|(25[0-5])\\.){3}((\\d\\d?+)|(1\\d\\d)|(2[0-4]\\d)|(25[0-5]))$";

    private static final Pattern PATTERN = Pattern.compile(REGEXIP);

    public static boolean isIpAddress(String ipAddress) {
        if (ipAddress == null) {
            throw new IllegalArgumentException("Passed ip address cannot be null");
        }
        Matcher matcher = PATTERN.matcher(ipAddress);
        return matcher.matches();
    }
}
