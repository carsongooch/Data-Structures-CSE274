import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

public class BinaryTreeJUnit {
	
	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testSum() {
		setup();
		assertEquals(0, empty.sum());
		assertEquals(60, sampleTree.sum());
		assertEquals(10, BinaryTree.leftsOnly(4).sum());
		assertEquals(15, BinaryTree.rightsOnly(5).sum());
		assertEquals(21, BinaryTree.zigZag(6).sum());
		assertEquals(5050, BinaryTree.completeTree(100).sum());
	}
	
	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testMax() {
		setup();
		assertEquals(15, sampleTree.max());
		assertEquals(4, BinaryTree.leftsOnly(4).max());
		assertEquals(5, BinaryTree.rightsOnly(5).max());
		assertEquals(6, BinaryTree.zigZag(6).max());
		assertEquals(100, BinaryTree.completeTree(100).max());
		assertEquals(1, BinaryTree.completeTree(1).max());
		assertEquals(-259, allNegatives.max());
	}
	
	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testOnlyChildCount() {
		setup();
		assertEquals(0, empty.onlyChildCount());
		assertEquals(2, sampleTree.onlyChildCount());
		assertEquals(1, singleNode.onlyChildCount());
		assertEquals(2, rootPlusLeft.onlyChildCount());
		assertEquals(2, rootPlusRight.onlyChildCount());
		assertEquals(1, rootPlusLeftRight.onlyChildCount());
		assertEquals(1, BinaryTree.completeTree(7).onlyChildCount());
		assertEquals(2, BinaryTree.completeTree(8).onlyChildCount());
		assertEquals(1, BinaryTree.completeTree(9).onlyChildCount());
		assertEquals(64, BinaryTree.zigZag(64).onlyChildCount());
		assertEquals(64, BinaryTree.rightsOnly(64).onlyChildCount());
		assertEquals(64, BinaryTree.leftsOnly(64).onlyChildCount());
	}

	
	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testIsBalanced() {
		setup();
		assertTrue(sampleTree.isBalanced());
		assertTrue(singleNode.isBalanced());
		assertTrue(rootPlusLeft.isBalanced());
		assertTrue(rootPlusRight.isBalanced());
		assertTrue(rootPlusLeftRight.isBalanced());

		for (int i = 10; i < 30; i++) {
			assertTrue(BinaryTree.completeTree(i).isBalanced());
		}

		BinaryTree singleton = BinaryTree.completeTree(1);
		BinaryTree lefts = BinaryTree.leftsOnly(2);
		BinaryTree rights = BinaryTree.rightsOnly(2);

		singleton.root.left = lefts.root;
		assertFalse(singleton.isBalanced());
		singleton.root.right = rights.root;
		assertTrue(singleton.isBalanced());

		lefts = BinaryTree.leftsOnly(3);
		rights = BinaryTree.rightsOnly(3);

		singleton.root.left = lefts.root;
		assertFalse(singleton.isBalanced());
		singleton.root.right = rights.root;
		assertFalse(singleton.isBalanced());
	}

	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testNodeCount() {
		setup();
		assertEquals(6, sampleTree.nodeCount());
		assertEquals(1, singleNode.nodeCount());
		assertEquals(2, rootPlusLeft.nodeCount());
		assertEquals(2, rootPlusRight.nodeCount());
		assertEquals(3, rootPlusLeftRight.nodeCount());
		assertEquals(50, BinaryTree.completeTree(50).nodeCount());
		assertEquals(63, BinaryTree.completeTree(63).nodeCount());
		assertEquals(64, BinaryTree.completeTree(64).nodeCount());
		assertEquals(20, BinaryTree.zigZag(20).nodeCount());
	}
	
	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testNodesAtLevel() {
		setup();
		assertEquals(1, sampleTree.nodesAtLevel(0));
		assertEquals(2, sampleTree.nodesAtLevel(1));
		assertEquals(3, sampleTree.nodesAtLevel(2));
		assertEquals(0, sampleTree.nodesAtLevel(3));
		assertEquals(0, sampleTree.nodesAtLevel(-1));
		
		assertEquals(1, singleNode.nodesAtLevel(0));
		assertEquals(0, singleNode.nodesAtLevel(1));

		BinaryTree full = BinaryTree.fullTree(5);
		int count = 1;
		for (int i = 0; i < 5; i++) {
			assertEquals(count, full.nodesAtLevel(i));
			count *= 2;
		}
		
		BinaryTree zigZag = BinaryTree.zigZag(10);
		for (int i = 0; i < 10; i++) {
			assertEquals(1, zigZag.nodesAtLevel(i));
		}
	}
	
	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testAllLeaves() {
		BinaryTree bt = new BinaryTree();
		assertEquals(0, bt.allLeaves().size());

		bt.add(17);
		Set<Integer> leaves = bt.allLeaves();
		assertEquals(1, leaves.size());
		assertTrue(leaves.contains(17));

		bt.add(10);
		leaves = bt.allLeaves();
		assertEquals(1, leaves.size());
		assertTrue(leaves.contains(10));

		bt.add(3);
		leaves = bt.allLeaves();
		assertEquals(2, leaves.size());
		assertTrue(leaves.contains(10));
		assertTrue(leaves.contains(3));

		bt.add(4);
		leaves = bt.allLeaves();
		assertEquals(2, leaves.size());
		assertTrue(leaves.contains(3));
		assertTrue(leaves.contains(4));
		
		BinaryTree full = BinaryTree.fullTree(3);
		leaves = full.allLeaves();
		assertEquals(8, leaves.size());
		for (int i = 8; i <= 15; i++) {
			assertTrue(leaves.contains(i));
		}
	}
	
	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testAllSums() {
		BinaryTree bt = new BinaryTree();
		assertEquals(0, bt.allSums().size());

		bt.add(17);
		Set<Integer> sums = bt.allSums();
		assertEquals(1, sums.size());
		assertTrue(sums.contains(17));

		bt.add(10);
		sums = bt.allSums();
		assertEquals(1, sums.size());
		assertTrue(sums.contains(27));

		bt.add(3);
		sums = bt.allSums();
		assertEquals(2, sums.size());
		assertTrue(sums.contains(27));
		assertTrue(sums.contains(20));

		bt.add(4);
		sums = bt.allSums();
		assertEquals(2, sums.size());
		assertTrue(sums.contains(20));
		assertTrue(sums.contains(31));
	}
	
	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testAllPaths() {
		BinaryTree bt = new BinaryTree();
		assertEquals(0, bt.allPaths().size());

		bt.add(17);
		Set<String> paths = bt.allPaths();
		assertEquals(1, paths.size());
		assertTrue(paths.contains("17"));

		bt.add(10);
		paths = bt.allPaths();
		assertEquals(1, paths.size());
		assertTrue(paths.contains("17 10"));

		bt.add(3);
		paths = bt.allPaths();
		assertEquals(2, paths.size());
		assertTrue(paths.contains("17 10"));
		assertTrue(paths.contains("17 3"));

		bt.add(4);
		paths = bt.allPaths();
		assertEquals(2, paths.size());
		assertTrue(paths.contains("17 10 4"));
		assertTrue(paths.contains("17 3"));
	}

	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testAllSumsIncludingPartial() {
		BinaryTree bt = new BinaryTree();
		assertEquals(0, bt.allSumsIncludingPartial().size());

		bt.add(17);
		Set<Integer> sums = bt.allSumsIncludingPartial();
		assertEquals(1, sums.size());
		assertTrue(sums.contains(17));

		bt.add(10);
		sums = bt.allSumsIncludingPartial();
		assertEquals(2, sums.size());
		assertTrue(sums.contains(17));
		assertTrue(sums.contains(27));

		bt.add(3);
		sums = bt.allSumsIncludingPartial();
		assertEquals(3, sums.size());
		assertTrue(sums.contains(17));
		assertTrue(sums.contains(27));
		assertTrue(sums.contains(20));

		bt.add(4);
		sums = bt.allSumsIncludingPartial();
		assertEquals(4, sums.size());
		assertTrue(sums.contains(17));
		assertTrue(sums.contains(27));
		assertTrue(sums.contains(20));
		assertTrue(sums.contains(31));
	}
	
	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testGreedySum() {
		BinaryTree bt = new BinaryTree();

		bt.add(17);
		assertEquals(17, bt.greedySum());

		bt.add(100);
		assertEquals(117, bt.greedySum());

		bt.add(3);
		assertEquals(117, bt.greedySum());

		bt.add(100);
		assertEquals(217, bt.greedySum());

		bt.add(200);
		assertEquals(317, bt.greedySum());

		bt.add(1000);
		assertEquals(317, bt.greedySum());
	}

	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testHeight() {
		setup();
		assertEquals(2, sampleTree.height());
		assertEquals(0, singleNode.height());
		assertEquals(1, rootPlusLeft.height());
		assertEquals(1, rootPlusRight.height());
		assertEquals(1, rootPlusLeftRight.height());
		assertEquals(5, BinaryTree.completeTree(50).height());
		assertEquals(5, BinaryTree.completeTree(63).height());
		assertEquals(6, BinaryTree.completeTree(64).height());
		assertEquals(19, BinaryTree.zigZag(20).height());
	}

	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testLeftmostValue() {
		setup();
		assertEquals(7, sampleTree.leftmostValue());
		assertEquals(1, singleNode.leftmostValue());
		assertEquals(2, rootPlusLeft.leftmostValue());
		assertEquals(1, rootPlusRight.leftmostValue());
		assertEquals(2, rootPlusLeftRight.leftmostValue());
		assertEquals(32, BinaryTree.completeTree(50).leftmostValue());
		assertEquals(32, BinaryTree.completeTree(63).leftmostValue());
		assertEquals(64, BinaryTree.completeTree(64).leftmostValue());
		assertEquals(1, BinaryTree.zigZag(20).leftmostValue());
		assertEquals(20, BinaryTree.leftsOnly(20).leftmostValue());
		assertEquals(1, BinaryTree.rightsOnly(20).leftmostValue());
	}

	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testLeafCount() {
		setup();
		assertEquals(3, sampleTree.leafCount());
		assertEquals(1, singleNode.leafCount());
		assertEquals(1, rootPlusLeft.leafCount());
		assertEquals(1, rootPlusRight.leafCount());
		assertEquals(2, rootPlusLeftRight.leafCount());
		assertEquals(25, BinaryTree.completeTree(50).leafCount());
		assertEquals(32, BinaryTree.completeTree(63).leafCount());
		assertEquals(32, BinaryTree.completeTree(64).leafCount());
		assertEquals(1, BinaryTree.zigZag(20).leafCount());
	}

	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testIsFull() {
		setup();
		assertFalse(sampleTree.isFull());
		assertTrue(singleNode.isFull());
		assertFalse(rootPlusLeft.isFull());
		assertFalse(rootPlusRight.isFull());
		assertFalse(BinaryTree.zigZag(3).isFull());
		assertTrue(rootPlusLeftRight.isFull());

		assertTrue(BinaryTree.completeTree(3).isFull());
		assertFalse(BinaryTree.completeTree(4).isFull());
		assertFalse(BinaryTree.completeTree(5).isFull());
		assertFalse(BinaryTree.completeTree(32).isFull());
		assertFalse(BinaryTree.completeTree(47).isFull());
		assertFalse(BinaryTree.completeTree(62).isFull());
	}

	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testToString() {
		setup();
		assertEquals("7 11 10 15 9 8", sampleTree.toString());
		assertEquals("1 3 5 4 2", BinaryTree.zigZag(5).toString());
	}

	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testInOrderTraversalPath() {
		setup();
		ArrayList<Integer> result = new ArrayList<>();
		for (int n : new int[] {7, 11, 10, 15, 9, 8}) result.add(n);		
		assertEquals(result, sampleTree.inOrderTraversalPath());
		
		result.clear();
		for (int n : new int[] {1, 3, 5, 4, 2}) result.add(n);		
		assertEquals(result, BinaryTree.zigZag(5).inOrderTraversalPath());
		
		result.clear();
		assertEquals(result, new BinaryTree().inOrderTraversalPath());
	}

	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testLevelOrderTraversalPath() {
		setup();
		ArrayList<Integer> result = new ArrayList<>();
		for (int n : new int[] {10, 11, 9, 7, 15, 8}) result.add(n);		
		assertEquals(result, sampleTree.levelOrderTraversalPath());
		
		result.clear();
		for (int n : new int[] {1, 2, 3, 4, 5}) result.add(n);		
		assertEquals(result, BinaryTree.zigZag(5).levelOrderTraversalPath());
		assertEquals(result, BinaryTree.leftsOnly(5).levelOrderTraversalPath());
		assertEquals(result, BinaryTree.rightsOnly(5).levelOrderTraversalPath());
		assertEquals(result, BinaryTree.completeTree(5).levelOrderTraversalPath());
	}

	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testCountValue() {
		setup();
		assertEquals(0, repeats.countValue(0));
		assertEquals(3, repeats.countValue(10));
		assertEquals(2, repeats.countValue(11));
		assertEquals(1, repeats.countValue(9));

		BinaryTree b = BinaryTree.completeTree(64);
		for (int i = 1; i <= 64; i++) {
			assertEquals(1, b.countValue(1));
		}

		for (int i = 1; i <= 20; i++) {
			assertEquals(1, zigZag.countValue(1));
		}
		assertEquals(0, zigZag.countValue(21));
		assertEquals(1, singleNode.countValue(1));
		assertEquals(0, singleNode.countValue(2));

		assertEquals(10, rightsOnlyAll50.countValue(50));
		assertEquals(0, rightsOnlyAll50.countValue(49));
	}
	
	@Test
	@Timeout(value = 500, unit = TimeUnit.MILLISECONDS)
	public void testExceptionThrowing() {
		setup();
		assertThrows(IllegalStateException.class, () -> {
			empty.max();
		});
		assertThrows(IllegalStateException.class, () -> {
			empty.leftmostValue();
		});
		assertThrows(IllegalStateException.class, () -> {
			empty.greedySum();
		});
	}


	BinaryTree allNegatives, empty, sampleTree, singleNode, rootPlusLeft, rootPlusRight, rootPlusLeftRight, rights, lefts, zigZag,
			complete31, complete32, complete47, repeats, rightsOnlyAll50;

	public void setup() {
		empty = new BinaryTree();
		sampleTree = BinaryTree.sampleTree();

		singleNode = new BinaryTree(); // single-node
		singleNode.root = new BinaryTree.Node(1);

		rootPlusLeft = new BinaryTree(); // root + left
		rootPlusLeft.root = new BinaryTree.Node(1);
		rootPlusLeft.root.left = new BinaryTree.Node(2);

		rootPlusRight = new BinaryTree(); // root + right
		rootPlusRight.root = new BinaryTree.Node(1);
		rootPlusRight.root.right = new BinaryTree.Node(2);

		rootPlusLeftRight = BinaryTree.completeTree(3); // root + left + right

		rights = BinaryTree.rightsOnly(5);
		lefts = BinaryTree.leftsOnly(9);
		zigZag = BinaryTree.zigZag(20);
		complete31 = BinaryTree.completeTree(31); // full, balanced, complete
		complete32 = BinaryTree.completeTree(32); // full+1: not full, but balanced,
										// complete
		complete47 = BinaryTree.completeTree(47); // full+ half of next l	evel: not full,
										// but balanced, complete, left full,
										// right full
		repeats = BinaryTree.manyRepeats();

		rightsOnlyAll50 = BinaryTree.rightsOnlyAll50(10);
		
		allNegatives = new BinaryTree();
		allNegatives.root = new BinaryTree.Node(-725);
		allNegatives.root.left = new BinaryTree.Node(-34587034);
		allNegatives.root.left = new BinaryTree.Node(-259);
	}
	
}