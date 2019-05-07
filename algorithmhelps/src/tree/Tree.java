package tree;

import java.util.ArrayList;
import java.util.List;

public class Tree {
    private int value;
    private List<Tree> children = new ArrayList<>();

    public Tree(int value) {
        this.value = value;
    }

    public int value() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public List<Tree> children() {
        return children;
    }

    public void setChildren(List<Tree> children) {
        this.children = children;
    }

    public void addChild(Tree t) {
        this.children.add(t);
    }
}
