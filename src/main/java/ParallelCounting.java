import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.stream.IntStream;

/**
 *  You can ADD any code you like in this class (new members, new methods, etc.).
 *  You can also add imports.
 */

public class ParallelCounting {


    static ExecutorService executor = Executors.newFixedThreadPool(1000);
    /**
     * Return the number of values equal to v using a parallel algorithm.
     *
     * @param values an array of numbers
     * @param v the value that you want to count
     * @param nThreads is a value between 1 and values.length
     * @return the number of elements equal to v in values (or 0 if no values are provided)
     *
     * Example: For
     *     values = [4.5f,3.2f,5.0f,6.6f,7.2f,1.5f,3.7f,5.8f,6.0f,9.0f,1.3f,2.3f,4.5f,1.5f]
     * and
     *     v = 4.5
     * the method returns 2 because the value 4.5 appears two times in the array.
     *
     * Try to give all threads more or less the same amount of work!
     */
    public static int parallelCount (Optional<float[]> values, float v, int nThreads) {
        if (!values.isPresent()) return 0;

        List<Integer> list = new ArrayList<>();

        float[] val = values.get();
        for (int i =0; i < nThreads;i++) {


            try {
                list.add(executor.submit(getCallable(i, val, nThreads, v)).get());
            } catch (InterruptedException e) {
            } catch (ExecutionException e) {
            }
        }

        return list.stream().mapToInt(i->i).sum();

    }

     static Callable<Integer> getCallable(int i,float[] val,  int nThreads, float v){
        return () -> (int) IntStream.range(0, val.length)
                .filter(j -> j % nThreads == i).mapToDouble(j -> val[j]).filter(f -> f == v).count();
     }
}