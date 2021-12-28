public class Leaf implements Visitable {

    int val;

    @Override
    public int accept(Visitor visitor) {
        return val;
    }

    public Leaf(int s) {
            this.val = s;
    }

    public int getValue() {
        return this.val;
    }
}
