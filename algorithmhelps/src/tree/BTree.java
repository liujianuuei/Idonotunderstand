package tree;

public class BTree {
    private int value;
    private BTree left;
    private BTree right;

    public BTree(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BTree left() {
        return left;
    }

    public void setLeft(BTree left) {
        this.left = left;
    }

    public BTree right() {
        return right;
    }

    public void setRight(BTree right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return value +"";
    }
}
