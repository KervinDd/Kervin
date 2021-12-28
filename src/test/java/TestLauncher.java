import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class TestLauncher {


    @Test
    public void test() {
        Counter[] counters = Launcher.init(new Thread[]{});
        assertEquals(0, counters.length);


        counters = Launcher.init(new Thread[]{new Thread(new Counter())});
        assertEquals(1, counters.length);
    }
}

