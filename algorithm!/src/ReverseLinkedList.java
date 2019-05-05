public class ReverseLinkedList {
    public static void main(String[] args) {
        LinkedList l = new LinkedList(1);
        LinkedList l2 = new LinkedList(2);
        LinkedList l3 = new LinkedList(3);
        LinkedList l4 = new LinkedList(4);
        LinkedList l5 = new LinkedList(5);
        LinkedList l6 = new LinkedList(6);

        l.setNext(l2);
        l2.setNext(l3);
        l3.setNext(l4);
        l4.setNext(l5);
        l5.setNext(l6);

        print(l);

        // Core Alg
        LinkedList prev = null;
        LinkedList cur = l;
        LinkedList next = null;
        while (cur != null) {
            next = cur.getNext();
            cur.setNext(prev);
            prev = cur;
            cur = next;
        }
        // END

        print(prev);
    }

    public static void print(final LinkedList l) {
        System.out.println();
        LinkedList t = l;
        while (t != null) {
            System.out.print(t.getValue());
            t = t.getNext();
        }
    }
}

class LinkedList {
    int value;
    LinkedList next;

    public LinkedList(int v) {
        this.value = v;
    }

    public int getValue() {
        return value;
    }

    public LinkedList getNext() {
        return next;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setNext(LinkedList next) {
        this.next = next;
    }

    public boolean hasNext() {
        return this.next != null;
    }
}


/*
// Core Alg
LinkedList self = l.getNext();
LinkedList a = l;
LinkedList b = null;
LinkedList c = null;
LinkedList d = null;
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