import java.util.ArrayList;
import java.util.Objects;

public class Tree extends MultiSet {
    // We recommend attempting this class last, as it hasn't been scaffolded for your team.
    // Even if your team doesn't have time to implement this class, it is a useful exercise
    // to think about how you might split up the work to get the Tree and TreeMultiSet
    // implemented.
    private int root;
    private ArrayList<Tree> subtrees;

    public Tree(int root, ArrayList<Tree> subtrees) {
        this.root = root;
        this.subtrees = Objects.requireNonNullElseGet(subtrees, ArrayList::new);
    }

    public Tree() {
        this(0, new ArrayList<>());
    }


    @Override
    void add(int item) {

    }

    @Override
    void remove(int item) {

    }

    @Override
    boolean contains(int item) {
        return false;
    }

    @Override
    boolean isEmpty() {
        return false;
    }

    @Override
    int count(int item) {
        return 0;
    }

    @Override
    int size() {
        return 0;
    }
}
