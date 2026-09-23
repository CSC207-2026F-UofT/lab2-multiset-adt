import java.util.ArrayList;
import java.util.Objects;

public class Tree {
    // We recommend attempting this class last, as it hasn't been scaffolded for your team.
    // Even if your team doesn't have time to implement this class, it is a useful exercise
    // to think about how d you might split up the work to get the Tree and TreeMultiSet
    // implemented.
    private Integer root;
    private ArrayList<Tree> subtrees;

    public Tree(int root, ArrayList<Tree> subtrees) {
        this.root = root;
        this.subtrees = Objects.requireNonNullElseGet(subtrees, ArrayList::new);
    }

    public Tree() {
        this(0, new ArrayList<>());
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Tree other = (Tree) obj;
        if (root != other.root) {
            return false;
        }
        return subtrees.equals(other.subtrees);

    }

    /**
     * Adds an item to the tree using the following rules:
     * 1. If the tree ie empty, the root is set to item
     * 2. If the tree has a root but no subtrees, create a new Tree with item and add it to the subtrees
     * 3. Otherwise pick a random number between 1 and 3, inclusive.
     *  - If the number is 3, then create a new Tree with item and add it to the subtrees
     *  - If the number is 1 or 2, then pick one of the existing subtrees at random, and
     *  recurse on that subtree.
     * @param item the item to add
     */
    public void insert(int item) {
        if (isEmpty()) {
            root = item;
        }
        else if (subtrees.isEmpty()) {
            subtrees.add(new Tree(item, new ArrayList<>()));
        }
        else {
            int randomNumber = (int) (Math.random() * 3) + 1;
            if (randomNumber == 3) {
                subtrees.add(new Tree(item, new ArrayList<>()));
            }
            else {
                int randomIndex = (int) (Math.random() * subtrees.size());
            }
        }
    }


    public void remove(int item) {

    }


    public boolean contains(int item) {
        if (isEmpty()) {
            return false;
        }
        else if (root == item) {
            return true;
        }
        else {
            for (Tree tree : subtrees) {
                if (tree.contains(item)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     *
     * @return boolean true if the tree is empty, which means the root is zero and the subtrees are empty
     */

    public boolean isEmpty() {
        return root == null && subtrees.isEmpty();
    }


    public int count(int item) {
        return 0;
    }

    /**
     * Returns the number of nodes in the tree.
     * @return the number of nodes in the tree
     */

    public int size() {
        if (isEmpty()) {
            return 0;
        }
       int size = 1;
       for (Tree tree : subtrees) {
           size += tree.size();
       }
       return size;
    }


    public String toString() {
        return "Tree{" + "root=" + root + ", subtrees=" + subtrees + '}';
    }


}
