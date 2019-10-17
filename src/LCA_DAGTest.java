import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

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
		
		dag.addEdge(1, 4);			
		dag.addEdge(1, 3);			
		dag.addEdge(2, 1);			
		dag.addEdge(3, 4);			  
		dag.addEdge(0, 1);			
		
		//	     4 --3
		//		  \ /
		//         1 
		//	      / \ 
		//	     2   0
		
		assertEquals("Checking no. of vertices", 5, dag.V());
		assertEquals("Checking no. of edges", 5, dag.E());
		assertEquals("Checking indegree for vertex 1", 2, dag.indegree(1));
		assertEquals("Checking outdegree for vertex 1", 2, dag.outdegree(1));
		assertEquals("[4, 3]", dag.adj(1).toString());
	}
	
	
	@Test
	public void exceptionTest() {	 		
		assertThrows(IllegalArgumentException.class, () -> new DAG(-1));
	}
	
	
	@Test
	public void testAddEdge(){
		DAG dag = new DAG(5);
		
		dag.addEdge(1,2);
		assertEquals("Checking addEdge", 1, dag.E());
		
		dag.addEdge(-6, 3);
		assertEquals("Checking addEdge with non-existing vertex v", 1, dag.E()); //no of edges is still 1
		
		dag.addEdge(1, 6);
		assertEquals("Checking addEdge with non-existing vertex w", 1, dag.E()); //no of edges is still 1
	}
	
	@Test 
	public void testIndegree() {
		DAG dag = new DAG(1);
		assertEquals("Checking indegree with invalid vertex", -1, dag.indegree(2));
	}
	
	@Test
	public void testOutdegree() {
		DAG dag = new DAG(1);
		assertEquals("Checking outdegree with invalid vertes", -1, dag.outdegree(2));
	}
	
	@Test
	public void testFindCycle() {
		DAG dag = new DAG(3);
		
		dag.addEdge(0, 1);			
		dag.addEdge(1, 2);	
		
		dag.findCycle(0);
		assertFalse("Checking for cycle in acyclic graph", dag.containsCycle());
	
		dag.addEdge(2, 0);
		dag.findCycle(0);
		assertTrue("Checking for cycle in cyclic graph", dag.containsCycle());
	}
	
	@Test 
	public void testLCA() {
		
		DAG dag = new DAG(10);
		
		//LCA test of graph with no edges
		assertEquals("Checking LCA of (5,8)", -1, dag.LCA(5, 8));
		
		dag.addEdge(0, 3);			
		dag.addEdge(0, 6);			
		dag.addEdge(1, 2);	
		
		//no common ancestors LCA test
		assertEquals("Checking LCA of vertices with no common ancestors (0,2)", -1, dag.LCA(0, 2));
				
		dag.addEdge(1, 8);			
		dag.addEdge(2, 5);			
		dag.addEdge(3, 1);			
		dag.addEdge(3, 5);			
		dag.addEdge(6, 1);			
		
		
		//		  6	  8
		//		 / \ / 		
		//		0	1 --2		
		//		 \ /   /
		//		  3	--5	
		
		
		//LCA sample tests
		assertEquals("Checking LCA of (5,8)", 3, dag.LCA(5, 8));
		assertEquals("Checking LCA of (2,5)", 2, dag.LCA(2, 5));
		assertEquals("Checking LCA of (2,6)", 6, dag.LCA(2, 6));
		
		//invalid vertices LCA tests
		assertEquals("Checking LCA of invalid vertices(-2,8)", -1, dag.LCA(-2, 8));
		assertEquals("Checking LCA of invalid vertices(2,12)", -1, dag.LCA(2, 12));		
		
		//cyclic LCA test
		dag.addEdge(5, 1);
		dag.findCycle(1);
		assertEquals("Checkint LCA of cyclic graph", -1, dag.LCA(5, 8));
		
	} 
}
