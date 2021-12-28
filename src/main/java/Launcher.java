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
    public static Counter[] init(Thread[] t){
        IntStream.range(0, t.length).forEach(i->t[i] = new Thread(new Counter(), i + ""));


//        t[0].setName("oli");
        return Arrays.stream(t).map(s->new Counter()).toArray(Counter[]::new);
    }
}
