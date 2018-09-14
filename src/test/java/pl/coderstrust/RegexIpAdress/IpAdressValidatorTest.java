package pl.coderstrust.RegexIpAdress;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class IpAdressValidatorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private long calculateExecutionTime (long startTime, long endTime) {
        return endTime - startTime;
    }

    private void printExecutionTime(long calculatedTime) {
        long hours = calculatedTime / 3_600_000;
        long hourRest = calculatedTime % 3_600_000;
        long min = hourRest / 60_000;
        long minutesRest = hourRest % 60_000;
        long sec = minutesRest / 1000;
        long ms = minutesRest % 1000;
        System.out.println(String.format("%d hours, %d min, %d sec, %d ms", hours, min, sec, ms));
    }

    @Ignore
    @Test
    public void testForEveryIpAddress() {
        String ipAdress;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                for (int k = 0; k < 256; k++) {
                    for (int l = 0; l < 256; l++) {
                        ipAdress = String.format("%d.%d.%d.%d", i, j, k, l);
                        assertTrue(IpAdressValidator.isIpAddress(ipAdress));
                    }
                }
            }
        }
        long endTime = System.currentTimeMillis();
        long calculatedExecutionTime = calculateExecutionTime(startTime, endTime);
        printExecutionTime(calculatedExecutionTime);
    }

    @Test
    @Parameters({
            "127.0.0.1",
            "0.0.0.0",
            "255.255.255.255",
            "1.1.1.1",
            "192.168.0.1"})
    public void testForValidIpAddresses(String ipAdress) {
        //when
        boolean result = IpAdressValidator.isIpAddress(ipAdress);

        //then
        assertTrue(result);
    }

    @Test
    @Parameters({
            "",
            " . . . ",
            " ",
            "0.0.0.-1",
            "!d21.44.1.2",
            "eps..2",
            "....",
            "@.!.2.//",
            "][.2.1.rty",
            "256.1.1.1",
            "3.400.1.53"})
    public void testForInvalidIpAddresses(String givenString) {
        //when
        boolean result = IpAdressValidator.isIpAddress(givenString);

        //then
        assertFalse(result);
    }

    @Test
    public void testForGivenStringAsNull() {
        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("String cannot be null");
        boolean result = IpAdressValidator.isIpAddress(null);
    }

    @Test
    @Parameters({
            "%d.1.1.1",
            "1.%d.1.1",
            "1.1.%d.1",
            "1.1.1.%d"})
    public void smartTestForValidIpAddresses(String ipAddressTemplate) {
        //then
        for (int i = 0; i< 256; i++) {
            String ipAdress = String.format(ipAddressTemplate, i);
            assertTrue(IpAdressValidator.isIpAddress(ipAdress));
        }
    }
}
