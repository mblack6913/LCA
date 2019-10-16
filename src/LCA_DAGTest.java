import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

class LCA_DAGTest {

	@Test
	void basicTest() {
		LCA tree = new LCA(); 
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
		LCA tree = new LCA();
		int n1 = 10, n2 = 14;
		Node t = tree.lca(tree.root, n1, n2);
		assertNull("Checking for null", t); 
	}
	
	@Test
	void testCheck() {
		LCA tree = new LCA(); 
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
	
	@Test
	public void testDAGConstructor() {
		DAG dag = new DAG(5);
		
		assertEquals("Checking indegree for unconnected vertex", 0, dag.indegree(1));
		assertEquals("Checking outdegree for unconnected vertex", 0, dag.indegree(1));
		
		dag.addEdge(1, 4);			//     4---3
		dag.addEdge(1, 3);			//	    \ /
		dag.addEdge(2, 1);			//       1 
		dag.addEdge(3, 4);			//	    / \   
		dag.addEdge(0, 1);			//     2   0
		
		assertEquals("Checking no. of vertices", 5, dag.V());
		assertEquals("Checking no. of edges", 5, dag.E());
		assertEquals("Checking indegree for vertex 1", 2, dag.indegree(1));
		assertEquals("Checking outdegree for vertex 1", 2, dag.outdegree(1));
		assertEquals("[4, 3]", dag.adj(1).toString());
	}
	
	
	@Test(expected=Exception.class)
	public void exceptionTest(){
		DAG dag = new DAG(-1);
	}
	
	
	@Test
	public void addEdge(){
		DAG dag = new DAG(5);
		
		dag.addEdge(1,2);
		assertEquals("Checking addEdge", 1, dag.E());
		
		dag.addEdge(-6, 3);
		assertEquals("Checking addEdge with non-existing vertex v", 1, dag.E()); //no of edges is still 1
		
		dag.addEdge(1, 6);
		assertEquals("Checking addEdge with non-existing vertex w", 1, dag.E()); //no of edges is still 1
	}
	
}