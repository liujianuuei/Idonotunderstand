package tree;

public class IsSameTree {

    public boolean isSameTree(BTree oneTree, BTree anotherTree) {
        if (oneTree == null && anotherTree == null) {
            return true;
        }
        if (oneTree == null || anotherTree == null) {
            return false;
        }

        if (oneTree.value() != anotherTree.value()) {
            return false;
        }

        return isSameTree(oneTree.left(), anotherTree.left()) && isSameTree(oneTree.right(), anotherTree.right());
    }
}
