package pl.coderstrust.foobar;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class FooBarTest {
    @Test
    public void testForNegative() {
        //given
        int size = -20;
        ArrayList<String>expected = new ArrayList<String>();
        expected.add("Invalid Value");

        //when
        ArrayList<String>array = new ArrayList<String>(FooBar.printFooBar(size));

        //then
        assertTrue(expected.equals(array));
    }

    @Test
    public void testForZero() {
        //given
        int size = 0;
        ArrayList<String>expected = new ArrayList<String>();
        expected.add("0 FooBar");

        //when
        ArrayList<String>array = new ArrayList<String>(FooBar.printFooBar(size));

        //then
        assertTrue(expected.equals(array));
    }

    @Test
    public void testForFirstTen() {
        //given
        int size = 9;
        ArrayList<String>expected = new ArrayList<String>();
        expected.add("0 FooBar");
        expected.add("1 ");
        expected.add("2 ");
        expected.add("3 Foo");
        expected.add("4 ");
        expected.add("5 Bar");
        expected.add("6 Foo");
        expected.add("7 ");
        expected.add("8 ");
        expected.add("9 Foo");

        //when
        ArrayList<String>array = new ArrayList<String>(FooBar.printFooBar(size));

        //then
        assertTrue(expected.equals(array));
    }
}