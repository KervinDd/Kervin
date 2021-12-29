import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.RecursiveAction;

public class ParallelMergeSort<E> extends RecursiveAction {

    private volatile E[] array, aux;
    private int lo,hi;
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

        if(hi-lo < 2){
            sort(lo,hi);
            return;
        }
        int mid =  (hi+lo) >>> 1;
        invokeAll(new ParallelMergeSort<E>(array, lo ,mid, aux, comp),new ParallelMergeSort<E>(array, mid, hi, aux, comp));
        merge(lo,mid,hi);

    }

	//Sort array between lo and hi using merge sort
    private void sort(int lo, int hi){
       return;
    }

    //merge two subarray and keep them sorted
    private void merge(int lo, int mid, int hi){
        int i = lo;
        int j = mid + 1;

        for (int ind = lo; ind <= hi; ind++) {
            if (i<=mid && ( j > hi || comp.compare(array[i],(array[j])) >= 0 )){
                aux[ind] = array[i];
                i++;
            } else if(j <= hi) {
                aux[ind] = array[j];
                j++;
            }
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