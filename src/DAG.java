import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class DAG
{
	private int V;						//no. of vertices
	private int E;						//no. of edges
	private int [] indegree;			//directed edges to vertex
	private int [] outdegree;			//directed edges from vertex
	private boolean containsCycle;		//	
	private boolean marked [];			//list of visited vertices
	private boolean stack [];			//
	private ArrayList<Integer>[] adj;   //adjacency list for vertices
	
	
	public DAG(int V){ 
		if(V < 0){
			throw new IllegalArgumentException("Cannot initialise with no vertices");
		}
		
		this.V = V;
		this.E = 0;
		indegree = new int[V];
		marked = new boolean[V];
		stack = new boolean[V];
		adj = (ArrayList<Integer>[]) new ArrayList[V];
		
		for(int v = 0; v < V; v++){
			adj[v] = new ArrayList<Integer>();
		}
	}
	
	//Returns current vertex
	public int V(){
		return V;
	}
	
	//Returns current edge
	public int E(){
		return E;
	}
	
	//Adds directed edge v->w
	public void addEdge(int v, int w){
		if(validateVertex(v) && validateVertex(w)) {
			adj[v].add(w);
			indegree[w]++;
			E++;
		}else{
			System.out.println("Edge " + v + "->" + w + " ignored due to non-existent vertex" );
		}		
	} 
	
	//Returns number of directed edges to vertex v
	public int indegree(int v){
		if(validateVertex(v)){
			return indegree[v];
		}
		else{
			return -1;
		}
	}
	
	//Returns number of directed edges from vertex v
	public int outdegree(int v){
		if(validateVertex(v)){
			return adj[v].size(); 
		}
		else{
			return -1;
		}
	}
	
	private boolean validateVertex(int v){
		if(v < 0 || v >= V){
			return false;
		}else{
			return true;
		}
	}
	
	//Returns adjacent vertices to v
	public Iterable<Integer> adj(int v){
		return adj[v];
	}
	
	public boolean containsCycle(){
		return containsCycle;
	}
	
	public void findCycle(int v){
		marked[v] = true;
		//stack[v] = true;
		
		for(int w : adj(v)){
			if(!marked[w]){
				findCycle(w);
			}else {//if(stack[w]){
				containsCycle = true;
				return;
			}
		}
		//stack[v] = false;
		marked[v] = false;
	}
	
	//Breadth-First search from source v
	public ArrayList<Integer> BFS(int v){
		ArrayList<Integer> order = new ArrayList<Integer>();
		boolean visited[] = new boolean[V]; //Marks vertices as not visit
		LinkedList<Integer> queue = new LinkedList<Integer>();
		
		visited[v] = true;
		queue.add(v);
		
		while(queue.size() != 0){
			v = queue.poll(); //Sets v to the head of the list
			order.add(v);
			
			//Find adjacent vertices to v
			//mark as visited (if not already) and enqueue
			Iterator<Integer> i = adj[v].listIterator();
			
			while(i.hasNext()){
				int n = i.next();
				if(!visited[n]){
					visited[n] = true;
					queue.add(n);
				}
			}
		}
		return order;
	}
	
	
	public DAG reverse(){
		DAG reverse = new DAG(V);
		for(int v = 0; v <V; v++){
			for(int w : adj(v)){
				reverse.addEdge(w, v);
			}		
		}
		return reverse;
	}
	
	
	public int LCA(int v, int w){
		findCycle(0);
		
		if(containsCycle){
			return -1;
		}else if(!(validateVertex(v)) || !(validateVertex(w))){    
			return -1;
		}else if(E == 0){
			return -1;
		}
		
		DAG reverse = reverse();
		
		ArrayList<Integer> array1 = reverse.BFS(v);
		ArrayList<Integer> array2 = reverse.BFS(w);
		ArrayList<Integer> commonAncestors = new ArrayList<Integer>();
		
		boolean found = false;
		
		for(int i = 0; i < array1.size(); i++){
			for(int j = 0; j < array2.size(); j++){
				if(array1.get(i) == array2.get(j)){
					commonAncestors.add(array1.get(i));
					found = true;
				}
			}
		}
		
		if(found){
			//Return first element in list (Lowest Common Ancestor)
			return commonAncestors.get(0);
		}
		else{
			return -1; //None found
		}
	}
}