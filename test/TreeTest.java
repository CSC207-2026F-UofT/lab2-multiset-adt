import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TreeTest {

    /** Helper to build a Tree with the given root and children. */
    private static Tree tree(int root, Tree... children) {
        return new Tree(root, new ArrayList<>(Arrays.asList(children)));
    }

    /**
     * Builds the tree:
     *        1
     *      / | \
     *     2  3  4
     *    / \     \
     *   5   2     6
     */
    private static Tree sampleTree() {
        return tree(1,
                tree(2, tree(5), tree(2)),
                tree(3),
                tree(4, tree(6)));
    }

    // ---------- constructor ----------

    @Test
    public void testConstructorNullSubtrees() {
        Tree t = new Tree(7, null);
        assertEquals(1, t.size());
        assertTrue(t.contains(7));
    }

    // ---------- isEmpty ----------

    @Test
    public void testIsEmptyDefaultConstructor() {
        Tree t = new Tree();
        assertTrue(t.isEmpty());
    }

    @Test
    public void testIsEmptyLeaf() {
        Tree t = tree(5);
        assertFalse(t.isEmpty());
    }

    @Test
    public void testIsEmptyWithSubtrees() {
        assertFalse(sampleTree().isEmpty());
    }

    // ---------- size ----------

    @Test
    public void testSizeEmpty() {
        assertEquals(0, new Tree(null, new ArrayList<>()).size());
    }

    @Test
    public void testSizeLeaf() {
        assertEquals(1, tree(5).size());
    }

    @Test
    public void testSizeOneLevel() {
        Tree t = tree(1, tree(2), tree(3));
        assertEquals(3, t.size());
    }

    @Test
    public void testSizeMultiLevel() {
        assertEquals(7, sampleTree().size());
    }

    @Test
    public void testSizeLinearChain() {
        Tree t = tree(1, tree(2, tree(3, tree(4))));
        assertEquals(4, t.size());
    }

    // ---------- contains ----------

    @Test
    public void testContainsEmpty() {
        // An empty tree contains nothing, including the sentinel root value 0.
        assertFalse(new Tree().contains(0));
    }

    @Test
    public void testContainsEmptyNonZero() {
        assertFalse(new Tree().contains(5));
    }

    @Test
    public void testContainsRoot() {
        assertTrue(sampleTree().contains(1));
    }

    @Test
    public void testContainsChild() {
        assertTrue(sampleTree().contains(3));
    }

    @Test
    public void testContainsDeepLeaf() {
        assertTrue(sampleTree().contains(6));
    }

    @Test
    public void testContainsMissing() {
        assertFalse(sampleTree().contains(99));
    }

    @Test
    public void testContainsNegative() {
        Tree t = tree(-3, tree(-7));
        assertTrue(t.contains(-7));
        assertFalse(t.contains(7));
    }

    // ---------- equals ----------

    @Test
    public void testEqualsSameInstance() {
        Tree t = sampleTree();
        assertEquals(t, t);
    }

    @Test
    public void testEqualsStructurallyEqual() {
        assertEquals(sampleTree(), sampleTree());
    }

    @Test
    public void testEqualsBothEmpty() {
        assertEquals(new Tree(), new Tree());
    }

    @Test
    public void testNotEqualsDifferentRoot() {
        assertNotEquals(tree(1, tree(2)), tree(9, tree(2)));
    }

    @Test
    public void testNotEqualsDifferentSubtrees() {
        assertNotEquals(tree(1, tree(2)), tree(1, tree(3)));
    }

    @Test
    public void testNotEqualsDifferentSubtreeOrder() {
        assertNotEquals(tree(1, tree(2), tree(3)), tree(1, tree(3), tree(2)));
    }

    @Test
    public void testNotEqualsNull() {
        assertNotEquals(null, sampleTree());
    }

    @Test
    public void testNotEqualsOtherType() {
        assertNotEquals("Tree", sampleTree());
    }

    // ---------- toString ----------

    @Test
    public void testToStringLeaf() {
        assertEquals("Tree{root=5, subtrees=[]}", tree(5).toString());
    }

    @Test
    public void testToStringNested() {
        assertEquals("Tree{root=1, subtrees=[Tree{root=2, subtrees=[]}]}",
                tree(1, tree(2)).toString());
    }

    // ---------- count ----------

    @Test
    public void testCountEmpty() {
        assertEquals(0, new Tree().count(1));
    }

    @Test
    public void testCountMissing() {
        assertEquals(0, sampleTree().count(99));
    }

    @Test
    public void testCountOnce() {
        assertEquals(1, sampleTree().count(6));
    }

    @Test
    public void testCountDuplicates() {
        // 2 appears both as an inner node and as a leaf
        assertEquals(2, sampleTree().count(2));
    }

    // ---------- add ----------

    @Test
    public void testAddToEmpty() {
        Tree t = new Tree();
        t.insert(5);
        assertTrue(t.contains(5));
        assertEquals(1, t.size());
    }

    @Test
    public void testAddToNonEmpty() {
        Tree t = sampleTree();
        t.insert(42);
        assertTrue(t.contains(42));
        assertEquals(8, t.size());
    }

    @Test
    public void testAddDuplicate() {
        Tree t = tree(3);
        t.insert(3);
        assertEquals(2, t.count(3));
        assertEquals(2, t.size());
    }

    // ---------- remove ----------

    @Test
    public void testRemoveFromEmpty() {
        Tree t = new Tree();
        t.remove(5);
        assertTrue(t.isEmpty());
    }

    @Test
    public void testRemoveMissing() {
        Tree t = sampleTree();
        t.remove(99);
        assertEquals(sampleTree(), t);
    }

    @Test
    public void testRemoveOnlyItem() {
        Tree t = tree(5);
        t.remove(5);
        assertTrue(t.isEmpty());
        assertEquals(0, t.size());
    }

    @Test
    public void testRemoveLeaf() {
        Tree t = sampleTree();
        t.remove(6);
        assertFalse(t.contains(6));
        assertEquals(6, t.size());
    }

    @Test
    public void testRemoveRoot() {
        Tree t = sampleTree();
        t.remove(1);
        assertFalse(t.contains(1));
        assertEquals(6, t.size());
        // all other items are still present
        for (int item : new int[]{2, 3, 4, 5, 6}) {
            assertTrue(t.contains(item), "missing " + item);
        }
    }

    @Test
    public void testRemoveInnerNodeKeepsDescendants() {
        Tree t = sampleTree();
        t.remove(4);
        assertFalse(t.contains(4));
        assertTrue(t.contains(6));
        assertEquals(6, t.size());
    }

    @Test
    public void testRemoveOnlyOneOccurrence() {
        Tree t = sampleTree();
        t.remove(2);
        assertEquals(1, t.count(2));
        assertEquals(6, t.size());
    }

    @Test
    public void testRemoveAllOccurrencesOneByOne() {
        Tree t = sampleTree();
        t.remove(2);
        t.remove(2);
        assertFalse(t.contains(2));
        assertEquals(0, t.count(2));
        assertEquals(5, t.size());
    }

    @Test
    public void testRemoveEverything() {
        Tree t = sampleTree();
        for (int item : new int[]{1, 2, 3, 4, 5, 2, 6}) {
            t.remove(item);
        }
        assertTrue(t.isEmpty());
        assertEquals(0, t.size());
    }

    // ---------- zero as a real value ----------

    @Test
    public void testZeroLeafIsNotEmpty() {
        assertFalse(tree(0).isEmpty());
    }

    @Test
    public void testZeroLeafSize() {
        assertEquals(1, tree(0).size());
    }

    @Test
    public void testContainsZero() {
        assertTrue(tree(1, tree(0)).contains(0));
    }

    @Test
    public void testCountZero() {
        assertEquals(2, tree(0, tree(0), tree(1)).count(0));
    }

    @Test
    public void testZeroLeafNotEqualEmpty() {
        assertNotEquals(new Tree(), tree(0));
    }

    // ---------- equals / hashCode with large values ----------

    @Test
    public void testEqualsLargeRoot() {
        // Integer values outside [-128, 127] are not cached, so this
        // catches comparing roots with == instead of equals().
        assertEquals(tree(1000), tree(1000));
    }

    @Test
    public void testEqualsLargeNestedValues() {
        assertEquals(tree(500, tree(-500)), tree(500, tree(-500)));
    }

    @Test
    public void testNotEqualsEmptyVsLeaf() {
        assertNotEquals(new Tree(), tree(5));
    }

    @Test
    public void testNotEqualsDifferentDepth() {
        assertNotEquals(tree(1, tree(2, tree(3))), tree(1, tree(2), tree(3)));
    }

    @Test
    public void testEqualsIsSymmetric() {
        Tree a = sampleTree();
        Tree b = sampleTree();
        assertEquals(a, b);
        assertEquals(b, a);
    }

    @Test
    public void testHashCodeEqualTrees() {
        assertEquals(sampleTree().hashCode(), sampleTree().hashCode());
    }

    @Test
    public void testHashCodeEmptyTrees() {
        assertEquals(new Tree().hashCode(), new Tree().hashCode());
    }

    @Test
    public void testHashCodeWorksInHashSet() {
        Set<Tree> set = new HashSet<>();
        set.add(sampleTree());
        assertTrue(set.contains(sampleTree()));
    }

    // ---------- insert (random placement, so check properties) ----------

    @RepeatedTest(20)
    public void testInsertIntoLeafBecomesChild() {
        Tree t = tree(1);
        t.insert(2);
        assertEquals(tree(1, tree(2)), t);
    }

    @RepeatedTest(20)
    public void testInsertKeepsExistingItems() {
        Tree t = sampleTree();
        t.insert(42);
        for (int item : new int[]{1, 2, 3, 4, 5, 6, 42}) {
            assertTrue(t.contains(item), "missing " + item);
        }
        assertEquals(2, t.count(2));
    }

    @RepeatedTest(20)
    public void testInsertManyItems() {
        Tree t = new Tree();
        for (int i = 1; i <= 50; i++) {
            t.insert(i);
        }
        assertEquals(50, t.size());
        for (int i = 1; i <= 50; i++) {
            assertEquals(1, t.count(i), "wrong count for " + i);
        }
    }

    @RepeatedTest(20)
    public void testInsertSameItemManyTimes() {
        Tree t = new Tree();
        for (int i = 0; i < 10; i++) {
            t.insert(7);
        }
        assertEquals(10, t.size());
        assertEquals(10, t.count(7));
    }

    @Test
    public void testInsertZeroIntoEmpty() {
        Tree t = new Tree();
        t.insert(0);
        assertFalse(t.isEmpty());
        assertTrue(t.contains(0));
        assertEquals(1, t.size());
    }

    @Test
    public void testInsertThenRemove() {
        Tree t = new Tree();
        t.insert(9);
        t.remove(9);
        assertTrue(t.isEmpty());
    }

    // ---------- insertChild ----------

    @Test
    public void testInsertChildIntoEmpty() {
        Tree t = new Tree();
        assertFalse(t.insertChild(5, 1));
        assertTrue(t.isEmpty());
    }

    @Test
    public void testInsertChildUnderRoot() {
        Tree t = tree(1);
        assertTrue(t.insertChild(2, 1));
        assertEquals(tree(1, tree(2)), t);
    }

    @Test
    public void testInsertChildUnderRootAppendsLast() {
        Tree t = tree(1, tree(2));
        assertTrue(t.insertChild(3, 1));
        assertEquals(tree(1, tree(2), tree(3)), t);
    }

    @Test
    public void testInsertChildUnderDirectChild() {
        Tree t = tree(1, tree(2), tree(3));
        assertTrue(t.insertChild(9, 3));
        assertEquals(tree(1, tree(2), tree(3, tree(9))), t);
    }

    @Test
    public void testInsertChildUnderDeepNode() {
        Tree t = sampleTree();
        assertTrue(t.insertChild(9, 6));
        assertEquals(tree(1,
                tree(2, tree(5), tree(2)),
                tree(3),
                tree(4, tree(6, tree(9)))), t);
    }

    @Test
    public void testInsertChildUnderDeepNodeInFirstSubtree() {
        Tree t = sampleTree();
        assertTrue(t.insertChild(9, 5));
        assertEquals(tree(1,
                tree(2, tree(5, tree(9)), tree(2)),
                tree(3),
                tree(4, tree(6))), t);
    }

    @Test
    public void testInsertChildParentMissing() {
        Tree t = sampleTree();
        assertFalse(t.insertChild(9, 99));
        assertEquals(sampleTree(), t);
    }

    @Test
    public void testInsertChildDuplicateParentUsesFirst() {
        // 2 appears as a child of 1 and as a grandchild; the first
        // appearance (the child of 1) should get the new node.
        Tree t = sampleTree();
        assertTrue(t.insertChild(9, 2));
        assertEquals(tree(1,
                tree(2, tree(5), tree(2), tree(9)),
                tree(3),
                tree(4, tree(6))), t);
    }

    @Test
    public void testInsertChildIncreasesSizeAndCount() {
        Tree t = sampleTree();
        t.insertChild(3, 4);
        assertEquals(8, t.size());
        assertEquals(2, t.count(3));
    }

    // ---------- average ----------

    @Test
    public void testAverageEmpty() {
        assertEquals(0.0f, new Tree().average(), 1e-6);
    }

    @Test
    public void testAverageLeaf() {
        assertEquals(5.0f, tree(5).average(), 1e-6);
    }

    @Test
    public void testAverageIncludesRoot() {
        // (10 + 2 + 3) / 3 = 5
        assertEquals(5.0f, tree(10, tree(2), tree(3)).average(), 1e-6);
    }

    @Test
    public void testAverageOverAllNodes() {
        // (1 + 2 + 5 + 2 + 3 + 4 + 6) / 7 = 23 / 7
        assertEquals(23.0f / 7, sampleTree().average(), 1e-5);
    }

    @Test
    public void testAverageUnbalancedTree() {
        // The average of all 4 nodes, not the average of the subtrees' averages.
        // (0 + 4 + 8 + 12) / 4 = 6, whereas averaging subtree averages gives something else.
        Tree t = tree(0, tree(4), tree(8, tree(12)));
        assertEquals(6.0f, t.average(), 1e-6);
    }

    @Test
    public void testAverageNegativeValues() {
        assertEquals(-2.0f, tree(-1, tree(-3)).average(), 1e-6);
    }

    @Test
    public void testAverageNonInteger() {
        assertEquals(1.5f, tree(1, tree(2)).average(), 1e-6);
    }

    // ---------- toString ----------

    @Test
    public void testToStringEmpty() {
        assertEquals("Tree{root=null, subtrees=[]}", new Tree().toString());
    }

    @Test
    public void testToStringMultipleChildren() {
        assertEquals("Tree{root=1, subtrees=[Tree{root=2, subtrees=[]}, Tree{root=3, subtrees=[]}]}",
                tree(1, tree(2), tree(3)).toString());
    }
}
