import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Launcher {

    /*
     * Instantiate and start each thread from "t" with its OWN Counter object,
     * then wait for all threads to finish and return the set of Counter objects
     * the threads ran on. Each thread must be named according to its index in the
     * "t" array.
     */
    public static Counter[] init(Thread[] t) {
//        Counter[] counters = Stream.generate(Counter::new).limit(t.length).toArray(Counter[]::new);

        Counter[] counters = new Counter[t.length];
        for( int i = 0; i< t.length; i++) counters[i] = new Counter();

        for( int i = 0; i< t.length; i++) t[i] = new Thread(counters[i], i + "");

        Arrays.stream(t).forEach(Thread::start);
        try {
            for (Thread thread : t) {
                thread.join();
            }
        }catch (InterruptedException e) {};


        Arrays.stream(t).forEach(s ->{
            try {
                s.join();}
            catch (InterruptedException e) {}
        } );


        return counters;
    }
}
