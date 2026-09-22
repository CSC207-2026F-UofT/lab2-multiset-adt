
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
        Node newNode =  new Node(item);
        newNode.next = front;
        front = newNode;
        size += 1;
    }

    public void remove(int item) {

    }

    public boolean contains(int item) {
        return false;
    }

    public boolean isEmpty() {
        return false;
    }


    public int count(int item) {
        int count = 0;
        Node curr = this.front;
        while (curr != null) {
            if (curr.item == item) {
                count += 1;
            }
            curr = curr.next;
        }
        return -1;
    }

    public int size() {
        return -1;
    }
}
