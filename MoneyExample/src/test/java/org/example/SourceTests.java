package org.example;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

public class SourceTests {

    @Test
    public void testDollarMultiplication() {
        // This test should test times(), which returns an object, so expected should be an object
        Money five = Money.dollar(5);
        assertEquals(Money.dollar(10), five.times(2));
        assertEquals(Money.dollar(15), five.times(3));
        
    }

    @Test
    // $5 should equal $5
    public void testEquality() {
        assertTrue(Money.dollar(5).equals(Money.dollar(5)));
        assertFalse(Money.dollar(5).equals(Money.dollar(6)));
        assertFalse(Money.franc(5).equals(Money.dollar(5)));
    }

    @Test
    public void testCurrency() {
        assertEquals("USD", Money.dollar(1).currency());
        assertEquals("CHF", Money.franc(1).currency());
    }
}
