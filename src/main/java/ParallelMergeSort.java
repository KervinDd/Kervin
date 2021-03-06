import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort<E> extends RecursiveAction {

    private volatile E[] array, aux;
    private int lo, hi;
    private Comparator<? super E> comp;

    private static final int threshold = 128;


    public ParallelMergeSort(E[] a, int lo, int hi, E[] aux, Comparator<? super E> comp) {
        this.lo = lo;
        this.hi = hi;
        this.array = a;
        this.aux = aux;
        this.comp = comp;
    }

    /*
     * Run a normal sort when the difference between hi and lo is under the threshold
     * Otherwise : Split the sub array in two and start the sort on each part of the array simultaneously
     */


    @Override
    protected void compute() {

        if (hi - lo < threshold) {
            sort(lo, hi);
            return;
        }
        int mid = (hi + lo) >>> 1;
        invokeAll(new ParallelMergeSort<E>(array, lo, mid, aux, comp),
                new ParallelMergeSort<E>(array, mid + 1, hi, aux, comp));
        merge(lo, mid, hi);
    }

    //Sort array between lo and hi using merge sort
    private void sort(int lo, int hi) {
//        if (hi - lo < 1) return;
//        if (hi - lo < 2) {
//            if (comp.compare(array[hi], array[lo]) <= 0) {
//                E temp = array[hi];
//                array[hi] = array[lo];
//                array[lo] = temp;
//
//            }
//            return;
//        }


        if (hi - lo > 0) {
            int mid = (lo + hi) / 2;
            sort(lo, mid);
            sort(mid + 1, hi);
            merge(lo, mid, hi);
        }
    }

    //merge two subarray and keep them sorted
    private void merge(int lo, int mid, int hi) {
        int i = lo;
        int j = mid + 1;

        for (int ind = lo; ind <= hi; ind++) {
            if (i <= mid && (j > hi || comp.compare(array[i], array[j]) <= 0)) {
                aux[ind] = array[i];
                i++;
            } else if (j <= hi) {
                aux[ind] = array[j];
                j++;
            }
        }

        for (int k = lo; k <= hi; k++) {
            array[k] = aux[k];
        }
    }

}


//int size = 1000;
//Integer[] array = new Integer[size];
//for(int i = 0 ; i < size ; i++){
//array[i] = rng.nextInt(10000);
//}
//ParallelMergeSort task = new ParallelMergeSort(array, 0, size-1, new Integer[size],Comparator.comparing(Integer::intValue));
//new ForkJoinPool().invoke(task);