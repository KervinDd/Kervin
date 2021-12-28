import org.junit.Test;

import static org.junit.Assert.*;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class SharedCounterTest {


    class SharedCounter {

        private final java.util.concurrent.locks.ReentrantLock lock = new ReentrantLock();
        private final java.util.concurrent.locks.Condition notZero = lock.newCondition();


        int count = 1;

        public void inc() {
            lock.lock();
            count++;
            notZero.signal();
            lock.unlock();
        }

        public void dec() {
            lock.lock();

            while (count == 0) {
                try {
                    notZero.await();
                } catch (InterruptedException e) {
                }
            }

            count--;
            lock.unlock();
        }

        int get() {
            return count;
        }


    }

    @Test
    public void test1() {
        SharedCounter counter = new SharedCounter();


        Thread t = new Thread(() -> counter.inc(), "inc1");
        Thread s = new Thread(() -> counter.dec(), "dec1");
        Thread s2 = new Thread(() -> counter.dec(), "dec2");
        Thread s3 = new Thread(() -> counter.inc(), "inc2");



        t.start();
        s.start();
        s2.start();
        s3.start();


        assertEquals(0, counter.get());

    }

}
