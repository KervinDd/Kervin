import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;


public class SharedCounterTest {


    class SharedCounter {

//        private final java.util.concurrent.locks.ReentrantLock lock = new ReentrantLock();
//        private final java.util.concurrent.locks.Condition notZero = lock.newCondition();
//        private final java.util.concurrent.locks.Condition notTop = lock.newCondition();


        //        int count = 0;
        Deque<Integer> queue = new LinkedList<>();

        synchronized public void inc() throws InterruptedException {
            while (queue.size() == 2) {
                wait();
            }

            queue.push(1);
            notify();
        }

        synchronized public void dec() throws InterruptedException {
            while (queue.size() == 0) {
                wait();
            }

            queue.pop();
            notify();
        }

        Deque<Integer> get() {
            return queue;
        }


    }

    @Test
    public void test1() throws InterruptedException {
        SharedCounter counter = new SharedCounter();


        Runnable inc = new Runnable() {
            @Override
            public void run() {
                try {
                    counter.inc();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable dec = new Runnable() {
            @Override
            public void run() {
                try {
                    counter.dec();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        List<Thread> list = List.of(
                new Thread(inc),
                new Thread(dec),
                new Thread(dec),
                new Thread(inc),
                new Thread(inc));

        list.stream().forEach(Thread::start);
        for (Thread thread : list) {
            thread.join();
        }

        assertEquals(1, counter.get().size());


        System.out.println("----------");

        counter.queue = new LinkedList<>();
        list = List.of(
                new Thread(inc),
                new Thread(inc),
                new Thread(inc),
                new Thread(dec),
                new Thread(dec)
        );


        list.stream().forEach(Thread::start);
        for (Thread thread : list) {
            thread.join();
        }
        assertEquals(1, counter.get().size());

    }

}
