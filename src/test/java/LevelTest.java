import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LevelTest {

    @Test
    public void testLevelSize(){
        assertEquals(0, new Level("").getSize());
        assertEquals(1, new Level("#").getSize());
        assertEquals(2, new Level("##").getSize());
        assertEquals(2, new Level("#\n#").getSize());
    }

    @Test
    public void testLevel(){
        assertEquals("", new Level("").toString());
        assertEquals("#", new Level("#").toString());
        assertEquals("-", new Level("-").toString());
        assertEquals("K", new Level("K").toString());
        assertEquals("D", new Level("D").toString());


        assertEquals("##", new Level("##").toString());
        assertEquals("#\\n#", new Level("#\n#").toString());
        assertEquals("#-K\\n-D-\\n#-K", new Level("#-K\n-D-\n#-K").toString());
    }
    @Test
    public void testFactory(){
        Factory fac = new ElementFactory();
        assertEquals(Wall.class, fac.getElement('#').getClass());
        assertEquals(Door.class, fac.getElement('D').getClass());
        assertEquals(Floor.class, fac.getElement('-').getClass());
        assertEquals(Key.class, fac.getElement('K').getClass());

    }

}
