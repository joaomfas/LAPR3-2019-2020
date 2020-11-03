/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.project.utils;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author joaoferreira
 */
public class ParsersTest {

    @Test
    public void ParsersConTest() {
        Parsers p = new Parsers();
        assertTrue(p instanceof Parsers);
    }

    /**
     * Test of parseInt method, of class Parsers.
     */
    @Test
    public void testParseCons() {
        System.out.println("testParseCons");
        Parsers ps = new Parsers();
        assertTrue(true);
    }

    /**
     * Test of parseInt method, of class Parsers.
     */
    @Test
    public void testParseInt_valido() {
        System.out.println("parseInt_valido");
        String string = "1";
        Integer expResult = 1;
        Integer result = Parsers.parseInt(string);
        assertEquals(expResult, result);
    }

    /**
     * Test of parseInt method, of class Parsers.
     */
    @Test
    public void testParseInt_null() {
        System.out.println("parseInt_null");
        String string = "";
        Integer expResult = null;
        Integer result = Parsers.parseInt(string);
        assertEquals(expResult, result);
    }

    /**
     * Test of parseDouble method, of class Parsers.
     */
    @Test
    public void testParseDouble_valido() {
        System.out.println("parseDouble_valido");
        String string = "1.234";
        Double expResult = 1.234;
        Double result = Parsers.parseDouble(string);
        assertEquals(expResult, result);
    }

    /**
     * Test of parseDouble method, of class Parsers.
     */
    @Test
    public void testParseDouble_null() {
        System.out.println("parseDouble_null");
        String string = "";
        Double expResult = null;
        Double result = Parsers.parseDouble(string);
        assertEquals(expResult, result);
    }

    /**
     * Test of parseString method, of class Parsers.
     */
    @Test
    public void testParseString_valido() {
        System.out.println("parseString1");
        String string = "abc";
        String expResult = "abc";
        String result = Parsers.parseString(string);
        assertEquals(expResult, result);
    }

    /**
     * Test of parseString method, of class Parsers.
     */
    @Test
    public void testParseString_null() {
        System.out.println("parseString2");
        String string = "";
        String expResult = null;
        String result = Parsers.parseString(string);
        assertEquals(expResult, result);
    }
	
	    /**
     * Test of parseString method, of class Parsers.
     */
    @Test
    public void testParseString_null2() {
        System.out.println("parseString3");
        String string = null;
        String expResult = null;
        String result = Parsers.parseString(string);
        assertEquals(expResult, result);
    }
}
