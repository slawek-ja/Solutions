package pl.coderstrust.ipadrress;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import java.util.concurrent.TimeUnit;

@RunWith(JUnitParamsRunner.class)
public class IpAddressValidatorTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    private long calculateExecutionTime(long startTime, long endTime) {
        return endTime - startTime;
    }

    private void printExecutionTime(long executionTime) {
        long hours = TimeUnit.MILLISECONDS.toHours(executionTime);
        executionTime -= TimeUnit.HOURS.toMillis(hours);
        long min = TimeUnit.MILLISECONDS.toMinutes(executionTime);
        executionTime -= TimeUnit.MINUTES.toMillis(min);
        long sec = TimeUnit.MILLISECONDS.toSeconds(executionTime);
        executionTime -= TimeUnit.SECONDS.toMillis(sec);
        long ms = executionTime;
        System.out.printf("%02d hours, %02d min, %02d sec, %02d ms", hours, min, sec, ms);
    }

    @Ignore
    @Test
    public void testForEveryIpAddress() {
        String ipAddress;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 256; i++) {
            for (int j = 0; j < 256; j++) {
                for (int k = 0; k < 256; k++) {
                    for (int l = 0; l < 256; l++) {
                        ipAddress = String.format("%d.%d.%d.%d", i, j, k, l);
                        assertTrue(IpAddressValidator.isIpAddress(ipAddress));
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
    public void testForValidIpAddresses(String ipAddress) {
        //when
        boolean result = IpAddressValidator.isIpAddress(ipAddress);

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
        boolean result = IpAddressValidator.isIpAddress(givenString);

        //then
        assertFalse(result);
    }

    @Test
    public void testForNullIpAddress() {
        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Passed ip address cannot be null");
        IpAddressValidator.isIpAddress(null);
    }

    @Test
    @Parameters({
            "%d.1.1.1",
            "1.%d.1.1",
            "1.1.%d.1",
            "1.1.1.%d"})
    public void smartTestForValidIpAddresses(String ipAddressTemplate) {
        //then
        for (int i = 0; i < 256; i++) {
            String ipAddress = String.format(ipAddressTemplate, i);
            assertTrue(IpAddressValidator.isIpAddress(ipAddress));
        }
    }
}
