import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LevelTest {

    @Test
    public void testLevelSize(){
        assertEquals(0, new Level("").getSize());
        assertEquals(1, new Level("#").getSize());
        assertEquals(2, new Level("##").getSize());
        assertEquals(2, new Level("#/n#").getSize());
    }

    @Test
    public void testLevel(){
        assertEquals("", new Level("").toString());
        assertEquals("#", new Level("#").toString());

        assertEquals("##", new Level("##").toString());
        assertEquals("#\\n#", new Level("#\n#").toString());
    }

}
