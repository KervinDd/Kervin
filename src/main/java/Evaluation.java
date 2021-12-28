public class Evaluation implements Visitor {

    @Override
    public int visit(Node visitable) {
        int left = visitable.getLeft().accept(this);
        int right = visitable.getRight().accept(this);

        if (visitable.getClass() == Add.class) return  left+right;
        if (visitable.getClass() == Sub.class) return  left-right;
        if (visitable.getClass() == Div.class) return  right == 0 ? -1000 : left/right;
        if (visitable.getClass() == Mult.class) return  left*right;

        return -10000;
    }

    @Override
    public int visit(Leaf visitable) {
        return visitable.getValue();
    }
}
