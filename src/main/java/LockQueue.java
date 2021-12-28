
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockQueue {

    public final static int SIZE = 100;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition notFull = lock.newCondition();
    private final Condition notEmpty = lock.newCondition();

    public int head = 0;
    public int tail = 0;
    public final Integer[] cells = new Integer[SIZE];
    public int count = 0;


    public Integer dequeue() {
        lock.lock();
        try {

            while (empty()) {
                try {
                    notEmpty.await();
                } catch (InterruptedException e) {
                }
            }
            head++;
            count--;

            notFull.signal();
            return cells[(head - 1) % SIZE];
        } finally {
            lock.unlock();
        }

    }


    public void enqueue(Integer i) {

        lock.lock();
        try {
            while (full()) {
                try {
                    notFull.await();
                } catch (InterruptedException e) {
                }
            }
            tail++;
            count++;
            notEmpty.signal();
        } finally {
            lock.unlock();
        }
    }

    public boolean full() {
        return this.count == SIZE;
    }

    public boolean empty() {
        return this.head == this.tail;
    }

    public int size() {
        return this.tail - this.head;
    }

}
