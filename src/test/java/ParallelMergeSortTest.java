import org.junit.Test;

import java.util.Comparator;
import java.util.concurrent.ForkJoinPool;
import static org.junit.Assert.*;


public class ParallelMergeSortTest {
    ForkJoinPool threadPool = new ForkJoinPool();
    Comparator<Integer> comp = Comparator.comparing(Integer::intValue);

    @Test
    public void test1() {
        Integer[] a;

        a = new Integer[] {1};
        threadPool.invoke(new ParallelMergeSort<Integer>(a, 0, a.length-1, new Integer[a.length], comp));
        assertEquals(1,(int) a[0]);

        a = new Integer[] {1,2};
        threadPool.invoke(new ParallelMergeSort<Integer>(a, 0, a.length-1, new Integer[a.length], comp));
        assertEquals(1,(int) a[0]);


        a = new Integer[] {2,1};
        threadPool.invoke(new ParallelMergeSort<Integer>(a, 0, a.length-1, new Integer[a.length], comp));
        assertEquals(1,(int) a[0]);


        a = new Integer[] {1,3,2};
        threadPool.invoke(new ParallelMergeSort<Integer>(a, 0, a.length-1, new Integer[a.length], comp));
        assertArrayEquals(new Integer[] {1,2,3}, a);

        a = new Integer[] {1,3,2, 4};
        threadPool.invoke(new ParallelMergeSort<Integer>(a, 0, a.length-1, new Integer[a.length], comp));
        assertArrayEquals(new Integer[] {1,2,3,4}, a);

        a = new Integer[] {1,3,2, 4, 6, 5};
        threadPool.invoke(new ParallelMergeSort<Integer>(a, 0, a.length-1, new Integer[a.length], comp));
        assertArrayEquals(new Integer[] {1,2,3,4,5,6}, a);

    }
}
