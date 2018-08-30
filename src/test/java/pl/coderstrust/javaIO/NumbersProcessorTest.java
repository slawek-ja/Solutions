package pl.coderstrust.javaIO;

import static org.junit.Assert.*;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class NumbersProcessorTest {

    public NumbersProcessor numbersProcessor;

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Before
    public void initialize() {
        numbersProcessor = new NumbersProcessor();
    }

    @Test
    @Parameters({
            "1  2  3, 1+2+3=6",
            " 2 3 5, 2+3+5=10",
            "4 6 7 1 , 4+6+7+1=18",
            "-2 9 5, (-2)+9+5=12",
            "7 -5 4, 7+(-5)+4=6",
            "4 5 7 -3, 4+5+7+(-3)=13",
            "-1 -6 -7, (-1)+(-6)+(-7)=-14",
            "2 6 -10, 2+6+(-10)=-2",
            "0 0 0, 0+0+0=0",
            "3, 3=3",
            "6, 6=6",
            "         ,"})
    public void testForValidArguments(String givenLine, String expectedLine) {
        //when
        String result = numbersProcessor.processLine(givenLine);

        //then
        assertEquals(expectedLine, result);
    }

    @Test
    @Parameters({
            "1 ! 2. 3",
            "0 ....4 22",
            "';lsy ndg 87 64  jjjd  23",
            "1 2 - 5",
            ""})
    public void testWithInvalidCharacter(String givenLine) {
        //given
        String expectedLine = "";

        //when
        String result = numbersProcessor.processLine(givenLine);

        //then
        assertEquals(expectedLine, result);
    }

    @Test
    public void testWhenLineIsNull() {
        //when
        String givenLine = null;

        //then
        thrown.expect(IllegalArgumentException.class);
        thrown.expectMessage("Line cannot be null");
        String result = numbersProcessor.processLine(givenLine);
    }
}
