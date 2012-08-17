package pref.test;

import junit.framework.Assert;
import org.junit.Test;
import pref.Value;

public class ValueTest {
    @Test
    public void toStringTest() {
        Assert.assertEquals("A", Value.Ace.toString());
    }

    @Test
    public void toValueTest() {
        Assert.assertEquals(6, Value.King.getIndex());
    }
}
