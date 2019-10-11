import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

class LCATest {

	@Test
	void basicTest() {
		BinaryTree tree = new BinaryTree(); 
        tree.root = new Node(20); 
        tree.root.left = new Node(8); 
        tree.root.right = new Node(22); 
        tree.root.left.left = new Node(4); 
        tree.root.left.right = new Node(12); 
        tree.root.left.right.left = new Node(10); 
        tree.root.left.right.right = new Node(14);  
        
        //        _20_
        //      /     \
        //    _8_      22
        //  /     \
        // 4      _12_
        //      /      \
        //     10      14
   
        int n1 = 10, n2 = 14; 
        Node t = tree.lca(tree.root, n1, n2); 
        assertEquals("Checking LCA of 10 and 14", 12, t.data);
        
   
        n1 = 14; 
        n2 = 8; 
        t = tree.lca(tree.root, n1, n2); 
        assertEquals("Checking LCA of 14 and 8", 8, t.data);
    
   
        n1 = 10; 
        n2 = 22; 
        t = tree.lca(tree.root, n1, n2); 
        assertEquals("Checking LCA of 10 and 20", 20, t.data);
	}

	@Test 
	void testNull() { 
		BinaryTree tree = new BinaryTree();
		int n1 = 10, n2 = 14;
		Node t = tree.lca(tree.root, n1, n2);
		assertNull("Checking for null", t); 
	}
	
	@Test
	void testCheck() {
		BinaryTree tree = new BinaryTree(); 
		int n1 = 10;
		assertFalse("Testing check with null", tree.check(tree.root, n1));
		
		
        tree.root = new Node(20); 
        tree.root.left = new Node(8); 
        tree.root.right = new Node(22); 
        tree.root.left.left = new Node(4); 
        tree.root.left.right = new Node(12); 
        tree.root.left.right.left = new Node(10); 
        tree.root.left.right.right = new Node(14);
        
        n1 = 11;
        assertFalse("Testing check with incorrect value", tree.check(tree.root, n1));
        
        n1 = 10;
        assertTrue("Testing check with correct value", tree.check(tree.root, n1));
        
	}
	
}
