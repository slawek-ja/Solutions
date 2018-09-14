package pl.coderstrust.RegexIpAdress;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class RegexIpAdressTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    public long calculateExecutionTime (long startTime, long endTime) {
        return endTime - startTime;
    }

    public String printResultTime (long calculatedTime) {
        long hours = calculatedTime / 3_600_000;
        long hourRest = calculatedTime % 3_600_000;
        long min = hourRest / 60_000;
        long minutesRest = hourRest % 60_000;
        long sec = minutesRest / 1000;
        long ms = minutesRest % 1000;
        return String.format("%d hours, %d min, %d sec, %d ms", hours, min, sec, ms);
    }

    @Test
    public void testForEveryPossibleCombination() {
        String ipAdress;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 256; i++) {
            System.out.println(i);
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
        System.out.println(printResultTime(calculateExecutionTime(startTime, endTime)));
    }

    @Test
    @Parameters({
            "127.0.0.1, true",
            "0.0.0.0, true",
            "255.255.255.255, true",
            "1.1.1.1, true",
            "192.168.0.1, true",
            "256.1.1.1, false",
            "3.400.1.53, false"})
    public void testForValidArguments(String ipAdress, boolean expected) {
        //when
        boolean result = IpAdressValidator.isIpAddress(ipAdress);

        //then
        assertEquals(expected, result);
    }

    @Test
    @Parameters({
            ", false",
            "0.0.0.-1, false",
            "!d21.44.1.2, false",
            "eps..2, false",
            "...., false",
            "@.!.2.//, false",
            "][.2.1.rty, false"})
    public void testForInvalidCharacters(String givenString, boolean expected) {
        //when
        boolean result = IpAdressValidator.isIpAddress(givenString);

        //then
        assertEquals(expected, result);
    }

    @Test
    public void testForGivenStringAsNull() {
        //when
        String example = null;

        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("String cannot be null");
        boolean result = IpAdressValidator.isIpAddress(example);
    }

    @Test
    @Parameters({
            "%d.1.1.1",
            "1.%d.1.1",
            "1.1.%d.1",
            "1.1.1.%d"})
    public void smartTest(String ipAddressTemplate) {
        //when
        String ipAdress;

        //then
        for (int i = 0; i< 256; i++) {
            ipAdress = String.format(ipAddressTemplate, i);
            assertTrue(IpAdressValidator.isIpAddress(ipAdress));
        }
    }
}
