package linkedlist;

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

    public LinkedList next() {
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

    public static void print(final LinkedList l) {
        System.out.println();
        LinkedList t = l;
        while (t != null) {
            System.out.print(t.getValue());
            t = t.getNext();
        }
    }

    public static LinkedList list() {
        LinkedList l = new LinkedList(1);
        LinkedList l2 = new LinkedList(2);
        LinkedList l3 = new LinkedList(3);
        LinkedList l4 = new LinkedList(4);
        LinkedList l5 = new LinkedList(5);
        LinkedList l6 = new LinkedList(6);

        LinkedList l7 = new LinkedList(7);

        l.setNext(l2);
        l2.setNext(l3);
        l3.setNext(l4);
        l4.setNext(l5);
        l5.setNext(l6);
        l6.setNext(l7);

        return l;
    }
}