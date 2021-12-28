import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TestLauncher {


    @Test
    public void test() {

        Thread[] t = {};
        Counter[] counters = Launcher.init(t);
        assertEquals(0, counters.length);

        t = new Thread[]{new Thread()};
        counters = Launcher.init(t);
        assertEquals("0", t[0].getName());
        assertEquals(counters[0].getRnd() - 1,counters[0].getCount());

        t = new Thread[]{new Thread(), new Thread()};
        counters = Launcher.init(t);
        assertEquals("0", t[0].getName());
        assertEquals("1", t[1].getName());
        assertEquals(counters[0].getRnd() - 1,counters[0].getCount());
        assertEquals(counters[1].getRnd() - 1,counters[1].getCount());
    }
}

