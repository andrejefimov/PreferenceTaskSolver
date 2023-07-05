package pref.test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pref.Value;

public class ValueTest {
    @Test
    public void toStringTest() {
        Assertions.assertEquals("A", Value.Ace.toString());
    }

    @Test
    public void toValueTest() {
        Assertions.assertEquals(6, Value.King.getIndex());
    }
}
