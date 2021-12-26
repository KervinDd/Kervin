
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;


public class Tests {


    @Test
    public void testLevel(){
        AbstractLevel aLevel = new Level();
        Factory factory = new ElementFactory();

    }

    @Test
    public void testOne(){
        LinkedList l = new LinkedList(new int[]{7});
        Sorter.sort(l);
        assertTrue(l.isSorted());

        l = new LinkedList(new int[]{1,2});
        Sorter.sort(l);
        assertTrue(l.isSorted());

        l = new LinkedList(new int[]{2,1});
        Sorter.sort(l);
        assertTrue(l.isSorted());

    }

    @Test
    public void testThree(){
        LinkedList l;
        BigDecimal a;

        l = new LinkedList(new int[]{1,2,3});
        Sorter.sort(l);
        assertTrue(l.isSorted());

        l = new LinkedList(new int[]{1,3,2});
        Sorter.sort(l);
        assertTrue(l.isSorted());

        l = new LinkedList(new int[]{3,1,2});
        Sorter.sort(l);
        assertTrue(l.isSorted());
    }

    @Test
    public void testFour() {
        LinkedList l;

        l = new LinkedList(new int[]{1, 2, 3, 4});
        Sorter.sort(l);
        assertTrue(l.isSorted());
    }

    @Test
    public void testExample(){
        LinkedList l = new LinkedList(new int[]{7, 8, 2, 22, 102, 1});
        Sorter.sort(l);
        assertTrue(l.isSorted());
    }
    @Test
    public void alreadySorted(){
        LinkedList l = new LinkedList(new int[]{1,3,4,6,77,89});
        Sorter.sort(l);
        assertTrue(l.isSorted());
    }
    @Test
    public void allSortedButOne(){
        LinkedList l = new LinkedList(new int[]{1,3,4,6,5,77,89});
        Sorter.sort(l);
        assertTrue(l.isSorted());
    }
    @Test
    public void allSortedButOne2(){
        LinkedList l = new LinkedList(new int[]{3,4,6,51,77,89,1});
        Sorter.sort(l);
        assertTrue(l.isSorted());
    }
    @Test
    public void allSortedButOne3(){
        LinkedList l = new LinkedList(new int[]{1,44,3,4,6,77,89});
        Sorter.sort(l);
        assertTrue(l.isSorted());
    }
    @Test
    public void allSortedButOne4(){
        LinkedList l = new LinkedList(new int[]{2,1,3,4,6,77,89});
        Sorter.sort(l);
        assertTrue(l.isSorted());
    }
    @Test
    public void reversedSorted(){
        LinkedList l = new LinkedList(new int[]{56,45,26,13,5,3,1});
        Sorter.sort(l);
        assertTrue(l.isSorted());
    }
    @Test
    public void random(){
        LinkedList l = new LinkedList(new int[]{56,45,26,13,5,3,12,1,4,6,77,89});
        Sorter.sort(l);
        assertTrue(l.isSorted());
    }
}
