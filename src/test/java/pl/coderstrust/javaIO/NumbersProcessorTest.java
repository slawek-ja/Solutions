package pl.coderstrust.javaIO;

import static org.junit.Assert.*;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class NumbersProcessorTest {

    public NumbersProcessor numbersProcessorClassTest = new NumbersProcessor();

    @Test
    @Parameters({
            "1 3 4 7, 1+3+4+7=15",
            "3 5 2 1, 3+5+2+1=11",
            "2 3, 2+3=5",
            "4 0, 4+0=4"})
    public void testForSimpleExample(String givenLine, String expectedLine) throws Exception {
        //when
        String result = numbersProcessorClassTest.processLine(givenLine);

        //then
        assertEquals(expectedLine, result);
    }

    @Test
    @Parameters({
            "-2 9 5, (-2)+9+5=12",
            "7 -5 4, 7+(-5)+4=6",
            "4 5 7 -3, 4+5+7+(-3)=13"})
    public void testForValidArgumentsWithNegativeNumbers(String givenLine, String expectedLine) throws Exception {
        //when
        String result = numbersProcessorClassTest.processLine(givenLine);

        //then
        assertEquals(expectedLine, result);
    }

    @Test
    @Parameters({
            "-1 -6 -7, (-1)+(-6)+(-7)=-14",
            "2 6 -10, 2+6+(-10)=-2",
            "3 0 2 -15, 3+0+2+(-15)=-10"})
    public void testForNegativeSum(String givenLine, String expectedLine) throws Exception {
        //when
        String result = numbersProcessorClassTest.processLine(givenLine);

        //then
        assertEquals(expectedLine, result);
    }

    @Test
    @Parameters({
            "1 ! 2. 3, 1 ! 2. 3",
            "0 ....4 22, 0 ....4 22",
            "';lsy ndg 87 64  jjjd  23, ';lsy ndg 87 64  jjjd  23",
            "1 2 - 5, 1 2 - 5"})
    public void testWithInvalidCharacter(String givenLine, String expectedLine) throws Exception {
        //when
        String result = numbersProcessorClassTest.processLine(givenLine);

        //then
        assertEquals(expectedLine, result);
    }

    @Test
    public void testWhenArgumentsAreZeros() throws Exception {
        //given
        String givenLine = "0 0 0 0";
        String expectedLine = "0+0+0+0=0";

        //when
        String resultLine = numbersProcessorClassTest.processLine(givenLine);

        //then
        assertEquals(expectedLine, resultLine);
    }

    @Test
    @Parameters({
            "3, 3=3",
            "2, 2=2",
            "27, 27=27"})
    public void testForOneArgument(String givenLine, String expectedLine) throws Exception {
        //when
        String resultLine = numbersProcessorClassTest.processLine(givenLine);

        //then
        assertEquals(expectedLine, resultLine);
    }

    @Test
    public void testForEmptyLine() throws Exception {
        //given
        String givenLine = "";
        String expectedLine = "";

        //when
        String resultLine = numbersProcessorClassTest.processLine(givenLine);

        //then
        assertEquals(expectedLine, resultLine);
    }
}
