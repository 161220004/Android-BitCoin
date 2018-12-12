package de.schildbach.wallet.ui;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReadWriteActivityTest {

    private ReadWriteActivity eg;

    @Before
    public void setUp() throws Exception {
        eg = new ReadWriteActivity();
    }
    @Test
    public void outofIntRange1() throws Exception {
        assertEquals(false, eg.outofIntRange("0"));
    }
    @Test
    public void outofIntRange2() throws Exception {
        assertEquals(false, eg.outofIntRange("999"));
    }
    @Test
    public void outofIntRange3() throws Exception {
        assertEquals(true, eg.outofIntRange("2147483648"));
    }
    @Test
    public void outofIntRange4() throws Exception {
        assertEquals(true, eg.outofIntRange("2147483647"));
    }
    @Test
    public void outofIntRange5() throws Exception {
        assertEquals(false, eg.outofIntRange("10000000000"));
    }
}