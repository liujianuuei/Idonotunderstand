package linkedlist;

public class ReverseLinkedList {
    public static void main(String[] args) {
        LinkedList list = LinkedList.list();

        LinkedList.print(list);

        // Core Alg
        list = reverse(list);
        // END

        LinkedList.print(list);
    }

    public static LinkedList reverse(LinkedList list) {
        LinkedList prev = null;
        LinkedList cur = list;
        LinkedList next = null;
        while (cur != null) {
            next = cur.getNext();
            cur.setNext(prev);
            prev = cur;
            cur = next;
        }
        return prev;
    }
}


/*
// Core Alg
linkedlist.LinkedList self = l.getNext();
linkedlist.LinkedList a = l;
linkedlist.LinkedList b = null;
linkedlist.LinkedList c = null;
linkedlist.LinkedList d = null;
while (a != null) {
    b = a.getNext();
    c = b.getNext();
    d = c != null ? c.getNext() : null;
    b.setNext(a);
    a.setNext(d);
    a = c;
}
// END
 */