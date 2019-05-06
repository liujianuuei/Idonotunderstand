package tree;

public class LCA {

    public BTree lowestCommonAncestor(BTree root, BTree oneTree, BTree anotherTree) {
        if (root == null || root == oneTree || root == anotherTree) {
            return root;
        }
        BTree left = lowestCommonAncestor(root.left(), oneTree, anotherTree);
        BTree right = lowestCommonAncestor(root.right(), oneTree, anotherTree);
        if (left == null) {
            return right;
        }
        if (right == null) {
            return left;
        }
        return root;
    }
}
