package tree;

public class Traversal {
    public void preorder(BTree tree) {
        if (tree == null) {
            return;
        }
        int v = tree.value();
        System.out.println(v);
        preorder(tree.left());
        preorder(tree.right());
    }

    public void inorder(BTree tree) {
        if (tree == null) {
            return;
        }
        inorder(tree.left());
        int v = tree.value();
        System.out.println(v);
        inorder(tree.right());
    }

    public void postorder(BTree tree) {
        if (tree == null) {
            return;
        }
        postorder(tree.left());
        postorder(tree.right());
        int v = tree.value();
        System.out.println(v);
    }

    public void dfs(Tree tree) {
        if (tree == null) {
            return;
        }
        int v = tree.value();
        System.out.println(v);
        for (Tree t : tree.children()) {
            dfs(t);
        }
    }

    public void bfs(Tree tree) {
        bfs(tree, true);
    }

    public void bfs(Tree tree, boolean root) {
        if (tree == null) {
            return;
        }
        if (root) {
            int v = tree.value();
            System.out.println(v);
        }
        for (Tree t : tree.children()) {
            int v = t.value();
            System.out.println(v);
        }
        for (Tree t : tree.children()) {
            bfs(t, false);
        }
    }
}
