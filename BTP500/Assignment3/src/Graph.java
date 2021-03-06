import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * @author Jake Deacon
 * Graph class for Assignment 3.  
 * Depth-first searching is required on a tree with many-many relationships associating them.
 *
 */

public class Graph {
	
	/**
	 * TreeMap<String, TreeSet<String>> represents a node structure in a graph;
	 * param1: String (Key) indicated the source/capability/binary, 
	 * param2: TreeSet<String> (value) holds all associated paths (src->cap, cap->bin, bin->src)
	 */
	private TreeMap<String, TreeSet<String>> connections;
	
	/** 
	 * Counters for edges and vertices
	 */
	private Integer vertCount;	// counter of each vertex in tree
	private Integer edgeCount;	// counter of each edge in tree

	/**
	 *	Adds an edge between 2 vertices in a Graph. 
	 * @param v1: Vertex to connect to v2.
	 * @param v2: Vertex to connect to v1.
	 * @return: boolean indicating status of add. False for failure.
	 */
	public void addEdge(String v1, String v2){
		if (this.connections.containsKey(v1) && this.connections.containsKey(v2))
			// both vertices found in Graph, add target to Source treeSet
			// make sure it's not already stored
			if (!this.connections.get(v1).contains(v2)){
				this.connections.get(v1).add(v2);
				this.edgeCount++;
			}			
	}
	
	/**
	 * Adds a Vertex to a Graph.
	 * @param v: Vertex to be added 
	 */
	public void addVert(String v){
		// add the string to the graph with a null tree (no connections yet)
		// if it's not already added to the list
		if (!this.connections.containsKey(v)){
			this.connections.put(v,  new TreeSet<String>());
			this.vertCount++;
		}
	}
	
	/**
	 * Returns the number of edges between vertices in a graph.
	 * @return: private variable Graph.edgeCount;
	 */
	public int getEdges(){
		return edgeCount;
	}
	
	/**
	 * Returns the number of vertices on a graph. 
	 * @return: private member variable Graph.VertCount
	 */
	public int getVerts(){
		return vertCount;
	}
	
	/**
	 * Checks the Graph for a provided matching string pattern, starting at the shortest possible path and working down through the graph. 
	 * Calls FindPattern for search logic.
	 * @param pat1: The pattern being matched against pat2 value.
	 * @param pat2: The pattern being tested against pat1 value .
	 */
	public boolean printMatchingEdges(String pat1, String pat2 ){
		String v1 = "";
		String v2 = "";
		
			// find all patterns in connections that match pat1
			for(Entry<String, TreeSet<String>> e : this.connections.entrySet()){
				if (e.getKey().contains(pat1)){
					// if connections contains pat1, 
					 v1 = e.getKey();
					 break;
				}
			}
			
			for(Entry<String, TreeSet<String>> e : this.connections.entrySet()){
				if (e.getKey().contains(pat2)){
					// if connections contains pat2, add to match array to be returned 
					 v2 = e.getKey();
					 break;
				}
			}		
		
		
		if (!v1.isEmpty()  && !v2.isEmpty())
			// iterates from smallest to largest path
			for (int ii=0; ii<vertCount; ii++){	
				if (depthFirst(v1,v2, ii))
					return true;	
			}
		return false;
	}
	/**
	 * Calls FindPattern to create a more user-friendly method when calling. 
	 * 
	 * @param origin - vertex 1 to test for relation
	 * @param target - vertex 1 to test for relation
	 * @param dep - depth to search into the tree
	 * @return
	 */
	public boolean depthFirst(String origin, String target, int dep ){
		Stack<String> pathList = new Stack<String>();
		return findPattern(origin, target, dep, pathList);
	}
	
	/**
	 * Depth-First Search for relation between vertex v1 and v2 found within depth 'dep' of the Graph.  
	 * @param origin: Origin vertex.
	 * @param target: Target vertex.
	 * @param dep: Used in Depth-first Searching through Graph.
	 * @param pat: Empty Array List used to store the path found.
	 */
	public boolean findPattern(String origin, String target, int dep , Stack<String> pathList){
		// iterator needed to go through the TreeSets of each node 
		Iterator<String> iter;
		// get iterator
		iter = this.connections.get(origin).iterator(); 
		
		// when trying to see if the current node is a valid path node, 
		// we add the origin to the path until we know it is wrong.
		pathList.push(origin);
				
		// haven't found the target within the provided depth, exit after removing prev. added
		if (dep == 0){
			if (origin.equals(target)) {
				for ( Iterator<String>i = pathList.iterator(); i.hasNext(); ){
					String path = i.next();
					System.out.print(path);
					if (i.hasNext())
						System.out.print("==>");
					else{
						System.out.print("\n");
						return true;
					}
				}
			}
			pathList.pop();
			return false;
		}
		
		// for each value in the TreeSet of the current node
		while(iter.hasNext()){
			// if value of origin is found, add to pat
			String dummy = iter.next();
			if (!pathList.contains(dummy))
				// recursive call
				 if (findPattern(dummy, target, dep-1, pathList))
					 return true;
		}// end while
		return false;
	}	// end method
	
	
	
	/**
	 * Constructor
	 * sets default counters to 0, initiailizes TreeMap.
	 */
	public Graph(){
		this.vertCount = 0;
		this.edgeCount = 0;	
		this.connections = new TreeMap<String, TreeSet<String>>(); 
	}	
}
