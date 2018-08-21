package pl.coderstrust.fibonacci;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;
@RunWith(JUnitParamsRunner.class)

public class FibonacciCheckerTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testForNegativeArgument() {
        //given
        long number = -29;

        //when
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Numbers below 0 are not allowed");
        boolean result = FibonacciChecker.isFibonacciNumber(number);
    }

    @Test
    public void testForZeroAsArgument() {
        //given
        long number = 0;
        boolean expected = true;

        //when
        boolean result = FibonacciChecker.isFibonacciNumber(number);

        //then
        assertEquals(expected, result);
    }

    @Test
    @Parameters({"1, true",
                 "2, true",
                 "3, true",
                 "5, true",
                 "12, false",
                 "23, false",
                 "89, true",})
    public void testForPositiveArguments(long number, boolean expected) {
        //when
        boolean result = FibonacciChecker.isFibonacciNumber(number);

        //then
        assertEquals(expected, result);
    }
}
