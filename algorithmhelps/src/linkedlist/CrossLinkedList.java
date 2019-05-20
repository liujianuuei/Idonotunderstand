package linkedlist;

public class CrossLinkedList {

    public static void main(String[] args) {
        LinkedList node = LinkedList.list();

        LinkedList.print(node);

        // Core Alg
        LinkedList[] nodes = new LinkedList[100];
        int max = -1;
        nodes[++max] = node;
        while (node.hasNext()) {
            node = node.next();
            nodes[++max] = node;
        }
        for (int i = 0; i <= max; i++) {
            if (i == max - i || i + 1 == max - i) {
                nodes[max - i].setNext(null);
                break;
            }
            nodes[i].setNext(nodes[max - i]);
            nodes[max - i].setNext(nodes[i + 1]);
        }
        // END

        LinkedList.print(nodes[0]);
    }
}
