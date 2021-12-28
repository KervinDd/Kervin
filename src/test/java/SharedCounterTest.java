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

        private final java.util.concurrent.locks.ReentrantLock lock = new ReentrantLock();
        private final java.util.concurrent.locks.Condition notZero = lock.newCondition();
        private final java.util.concurrent.locks.Condition notTop = lock.newCondition();


//        int count = 0;
        Deque<Integer> queue = new LinkedList<>();

        public void inc() {
            lock.lock();

            while (queue.size() == 2) {
                try {
                    notTop.await();
                } catch (InterruptedException e) {
                }
            }

//            count++;
            queue.push(1);
            notZero.signal();
            lock.unlock();
        }

        public void dec() {
            lock.lock();

            while (queue.size() == 0) {
                try {
                    notZero.await();
                } catch (InterruptedException e) {
                }
            }

//            count--;
            queue.pop();
            notTop.signal();
            lock.unlock();
        }

        Deque<Integer> get() {
            return queue;
        }


    }

    @Test
    public void test1() throws InterruptedException {
        SharedCounter counter = new SharedCounter();


        List<Thread> list = List.of(
                new Thread(() -> counter.inc()),
                new Thread(() -> counter.dec()),
                new Thread(() -> counter.dec()),
                new Thread(() -> counter.inc()),
                new Thread(() -> counter.inc()));

        list.stream().forEach(Thread::start);
        for (Thread thread : list) {
            thread.join();
        }


        assertEquals(1, counter.get().size());

        SharedCounter counter2 = new SharedCounter();
        list = List.of(
                new Thread(() -> counter2.inc()),
                new Thread(() -> counter2.dec()),
                new Thread(() -> counter2.dec()),
                new Thread(() -> counter2.inc()),
                new Thread(() -> counter2.inc()),
                new Thread(() -> counter2.inc()),
                new Thread(() -> counter2.inc()),
                new Thread(() -> counter2.inc()),
                new Thread(() -> counter2.inc()),
                new Thread(() -> counter2.dec()),
                new Thread(() -> counter2.dec()),
                new Thread(() -> counter2.dec()),
                new Thread(() -> counter2.dec()),
                new Thread(() -> counter2.dec()),
                new Thread(() -> counter2.inc()));


                list.stream().forEach(Thread::start);
        for (Thread thread : list) {
            thread.join();
        }
        assertEquals(1, counter2.get().size());

    }

}
