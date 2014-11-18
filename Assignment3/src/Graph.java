import java.util.Iterator;
import java.util.ArrayList;
import java.util.Map.Entry;
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
	private int vertCount;	// counter of each vertex in tree
	private int edgeCount;	// counter of each edge in tree

	/**
	 *	Adds an edge between 2 vertices in a Graph. 
	 * @param v1: Vertex to connect to v2.
	 * @param v2: Vertex to connect to v1.
	 * @return: boolean indicating status of add. False for failure.
	 */
	public boolean addEdge(String v1, String v2){
		if (this.connections.containsKey(v1) && this.connections.containsKey(v2)){
			// both vertices found in Graph, add target to Source treeSet
			// make sure it's not already stored
			if (!this.connections.get(v1).contains(v2)){
				this.connections.get(v1).add(v2);
				this.edgeCount++;
			}
			return true;
			
		}else
			return false;
		
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
	 * Prints a path between vertex v1 and v2 found within depth 'dep' of the Graph.  
	 * @param origin: Origin vertex.
	 * @param target: Target vertex.
	 * @param dep: Used in Depth-first Searching through Graph.
	 * @param pat: Empty Array List used to store the path found.
	 * @return: ArrayList<String> with the path between the origin and target vertices 
	 * 			stored at progressively increasing indices.
	 */
	public ArrayList<String> printPath(String origin, String target, int dep, ArrayList<String> pat){
		// iterator needed to go through the TreeSets of each node 
		Iterator<String> iter;
		iter = this.connections.get(origin).iterator();
		// when trying to see if the current node is a valid path node, 
		// we add the origin to the path until we know it is wrong.
		pat.add(origin);
				
		if (origin.equals(target)) {
			// print the whole path
			System.out.println(pat);
			return pat;
		}
		
		// haven't found the target within the provided depth,
		if (dep == 0){	
			pat.remove(origin);
			return null;
		}
		else{
			// for each value in the TreeSet of the current node
			while(iter.hasNext()){
				// if value of origin is found, add to pat
				String dummy = iter.next();
				if (!pat.contains(dummy))
					printPath(dummy, target, dep-1, pat);
			}
			// if nothing has returned yet, we know no matches were found
			// so we pull this origin from the path (it is incorrect).
			pat.remove(origin);
			return pat;		
		}
	}
	
	/**
	 * Checks the Graph for a provided matching string pattern. 
	 * @param pat: The pattern being matched against a string value
	 * @return: an ArrayList of Strings which match the provided pattern 
	 */
	public ArrayList<String> matchString(String pat){
		// ArrayList for return
		ArrayList<String> match = new ArrayList<String>();
		
		// find all patterns in connections that match pat
		for(Entry<String, TreeSet<String>> e : this.connections.entrySet()){
			if (e.getKey().contains(pat))
				// if connections contains pat, add to match array to be returned 
				match.add(e.getKey());			
		}
		return match;
	}
	
	/**
	 * Constructor
	 * sets default counters to 0, initiailizes TreeMap.
	 */
	public Graph(){
		this.vertCount =0;
		this.edgeCount=0;	
		this.connections = new TreeMap<String, TreeSet<String>>(); 
	}	
}
