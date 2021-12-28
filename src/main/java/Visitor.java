public interface Visitor {

    public int visit(Leaf visitable);

    public int visit(Node node);
}
