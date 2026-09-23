import java.util.ArrayList;
import java.util.Objects;

public class Tree {
    // We recommend attempting this class last, as it hasn't been scaffolded for your team.
    // Even if your team doesn't have time to implement this class, it is a useful exercise
    // to think about how d you might split up the work to get the Tree and TreeMultiSet
    // implemented.
    private Integer root;
    private ArrayList<Tree> subtrees;

    public Tree(Integer root, ArrayList<Tree> subtrees) {
        this.root = root;
        this.subtrees = Objects.requireNonNullElseGet(subtrees, ArrayList::new);
    }

    public Tree() {
        this(null, new ArrayList<>());
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
     * Override the hashcode method to return due to the overridden equals method
     * @return the hashcode of the root and subtrees
     */
    @Override
    public int hashCode() {
        return Objects.hash(root, subtrees);
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

    /**
     * Inserts child into the tree as a child of parent.
     * @param child the child to be inserted
     * @param parent the parent of the child. If parrent appears more than once, choose the first appearance.
     * @return boolean true if the child was inserted, false if no parent was found
     */
    public boolean insertChild(int child, int parent) {
        if (isEmpty()) {
            return false;
        }
        else if (root == parent) {
            subtrees.add(new Tree(child, new ArrayList<>()));
            return true;
        }
        else {
            for (Tree tree : subtrees) {
                if (tree.root == parent) {
                    tree.subtrees.add(new Tree(child, new ArrayList<>()));
                    return true;
                }
            }
        }
        return false;
    }

    /** Returns the average of all the values in the tree.
     * @return the average of all the values in the tree
     * **/
    public float average() {
        if (isEmpty()) {
            return 0.0f;
        }
        else {
            float sum = 0.0f;
            for (Tree tree : subtrees) {
                sum += tree.average();
            }
            return sum / subtrees.size();

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

    /**
     * Counts the number of times item appears in the tree.
     * @param item the item to count
     * @return the number of times item appears in the tree.
     */
    public int count(int item) {
        if (isEmpty()) {
            return 0;
        }
        else {
            int count = 0;
            if (root == item) {
                count += 1;
            }
            for (Tree tree : subtrees) {
                count += tree.count(item);
            }
            return count;
        }
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
