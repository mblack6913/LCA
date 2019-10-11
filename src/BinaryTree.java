
public class BinaryTree 
{ 
    Node root; 
       
    public Node lca(Node node, int n1, int n2)  
    { 
        if (node == null) 
            return null; 
   
        // If both n1 and n2 are smaller than root, then LCA lies in left 
        if (node.data > n1 && node.data > n2) 
            return lca(node.left, n1, n2); 
   
        // If both n1 and n2 are greater than root, then LCA lies in right 
        if (node.data < n1 && node.data < n2)  
            return lca(node.right, n1, n2); 
   
        return node; 
    } 
    
    //Verifies if the value is in the tree
    public boolean check (Node x, int data)
    {
    	if (x == null) return false;
    	else if (x.data == data) return true;
    	if (check(x.left, data) == true || check(x.right, data) == true) return true;
    	
    	return false;
    } 
} 

