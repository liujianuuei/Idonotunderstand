package tree;

public class IsSubTree {

    private IsSameTree isSameTree = new IsSameTree();

    public boolean isSubtree(BTree parentTree, BTree subTree) {
        if (parentTree == null) {
            return false;
        }
        if (isSameTree.isSameTree(parentTree, subTree)) {
            return true;
        }
        return isSubtree(parentTree.left(), subTree) || isSubtree(parentTree.right(), subTree);
    }
}
