
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
        while (empty()) {
            try {
                notEmpty.await();
            } catch (InterruptedException e) {
            }
        }
        head++;

        notFull.signal();
        return cells[head - 1];
    }


    public void enqueue(Integer i) {

        lock.lock();
        while (full()) {
            try {
                notFull.await();
            } catch (InterruptedException e) {
            }
        }
        tail++;
        count++;
        notEmpty.signal();
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
