
// Node is only used inside the LinkedList class, so we define it in the same file;
// there can only be one public class in a file, but there can also be non-public classes.
class Node {
    int item;
    Node next;
    Node(int item) {
        this.item = item;
    }
}


public class LinkedListMultiSet extends MultiSet {

    // a linked list initially is empty
    private Node front;
    private int size;

    public void add(int item) {
        Node newNode = new Node(item);
        newNode.next = front;
        front = newNode;
        size += 1;
    }

    public void remove(int item) {
        // remove one instance of item
        // check from first
        if (front == null) {
            return;
        }

        if (front.item == item) {
            front = front.next;
            return;
        }

        Node p = front;
        while (p.next != null) {
            if (p.next.item == item) {
                p.next = p.next.next;
                return;
            }
            p = p.next;
        }
    }

    public boolean contains(int item) {
        Node p = front;
        while (p != null) {
            if (p.item == item) {
                return true;
            }
            p = p.next;
        }

        return false;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public int count(int item) {
        int count = 0;
        Node p = front;
        while (p != null) {
            if (p.item == item) {
                count++;
            }
            p = p.next;
        }

        return count;
    }

    public int size() {
        return size;
    }
}
