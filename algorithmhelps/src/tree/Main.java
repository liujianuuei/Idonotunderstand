package tree;

public class Main {
    public static void main(String[] args) {
        BTree bt = new BTree(3);
        BTree bt1 = new BTree(7);
        BTree bt2 = new BTree(8);
        BTree bt3 = new BTree(6);
        BTree bt4 = new BTree(9);
        BTree bt5 = new BTree(5);
        BTree bt6 = new BTree(4);

        bt.setLeft(bt1);
        bt.setRight(bt2);
        bt1.setLeft(bt3);
        bt1.setRight(bt4);
        bt4.setLeft(bt5);
        bt4.setRight(bt6);


        Tree t = new Tree(3);
        Tree t1 = new Tree(7);
        Tree t2 = new Tree(8);
        Tree t3 = new Tree(6);
        Tree t4 = new Tree(9);
        Tree t5 = new Tree(5);
        Tree t6 = new Tree(4);

        t.addChild(t1);
        t.addChild(t2);
        t1.addChild(t3);
        t1.addChild(t4);
        t4.addChild(t5);
        t4.addChild(t6);

        //System.out.println(new IsSubTree().isSubtree(t4, t3));

        //System.out.println(new LCA().lowestCommonAncestor(t, t3, t5));

        //new Traversal().postorder(bt);

        new Traversal().bfs(t);
    }
}
