public class Tree {
    // We recommend attempting this class last, as it hasn't been scaffolded for your team.
    // Even if your team doesn't have time to implement this class, it is a useful exercise
    // to think about how you might split up the work to get the Tree and TreeMultiSet
    // implemented.
    /// A recursive tree data structure, which provides required of the
    ///        MultiSet ADT. See TreeMultiSet, which is the next class defined.
    ///
    ///        This is a simplified version of the Tree data structure
    ///        adapted from CSC148.

    private int root;
    private Tree[] subtrees;

    /**
     * Constructor for an empty tree
     */
    public Tree() {}

    /**
     * Alternate constructor for single node tree
     * @param root
     */
    public Tree(int root) {}

    /**
     * Alternate constructor for multi-node tree
     * @param root
     * @param subtrees
     */
    public Tree(int root, Tree[] subtrees) {}

    public boolean isEmpty() {
        return false; // TODO: Implement
    }

    public int len() {
        return 0; // TODO: Implement
    }

    public int count(int item) {
        return 0; // TODO: Implement
    }

    @Override
    public String toString() {
        return super.toString(); // TODO: Implement
    }

    private String toStringIndented() {
        return this.toString(); // TODO: Implement
    }

    private String toStringIndented(int depth) {
        return this.toString(); // TODO: Implement
    }



    public double average() {
        return 0.0; // TODO: Implement
    }

    private int[] averageHelper() {
        return new int[] {0,0}; // TODO: Implement
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj); // TODO: Implement
    }

    public boolean contains(int item) {
        return false; // TODO: Implement
    }

    public int[] leaves() {
        return new int[] {}; // TODO: Implement
    }

    // Mutating methods

    public boolean deleteItem(int item) {
        return False; // TODO: Implement
    }

    private void deleteRoot() {
        // TODO: Implement
    }

    private int extractLeaf() {
        return 0; // TODO: Implement
    }

    public void insert(int item) {
        // TODO: Implement
    }

    public boolean insert(int item, int parent) {
        return false; // TODO: Implement
    }



}
