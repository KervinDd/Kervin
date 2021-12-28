class Node implements Visitable {

    private Visitable left;
    private Visitable right;

    public Node(Visitable left, Visitable right){
        this.left = left;
        this.right = right;
    }

    public Visitable getLeft() {
        return left;
    }

    public Visitable getRight() {
        return right;
    }

    @Override
    public int accept(Visitor visitor) {
        return visitor.visit(this);
    }

}
