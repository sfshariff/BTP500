import java.util.ArrayList;

public class Main {
 
 public static void main(String[] args) {
	 
	 /** 
	  * Make a new Graph object
	  */
	 // Graph test = new Graph();
	 
	 /**
	  * a Boolean indicating successful Adds
	  */
	 // boolean bAddedSuccessfully;
	 
	 /**
	  * As you load the GOAL CSV data line by line, you can call addVert(v) to make new vertices in the graph object.
	  * This is only required for sources (goals) because an edge only connects 2 sources.
	  */
	 // Line by line through the CSV
	 // test.addVert(v);  /* will make the initial vertices (sources). although the CSV is non repeating, I've handled for repeating values in the add.*/  
  
	 /** 
	  * For each Source, we'll need to figure out:
	  *  	how many capabilities it has,  
	  *  	how many binaries are produced by EACH capability, 
	  *  	which Sources are created by EACH binary. 
	  *  
	  *  And then connect the respective SOURCES by using test.addEdge(src1, src2)
	  */
	 
	  /**
	   * We can get all the vertices and edges by calling their respective get methods:
	   */
	   // test.getVerts()
	   // test.getEdges()
	   //System.out.println("Vertices: "+test.getVerts()+" Edges: "+test.getEdges());
	   
	 /** String Fragment Matching is fairly simple:
	  * We will need an ArrayList to hold the returned list of matching paths
	  * ArrayList<String> matches = new ArrayList();
	  * 
	  * matches = test.matchString(string src);
	  * 
	  * param src: the substring you're looking for 
	  * return matches: the ArrayList with all results that contain instances of the substring 
	  */
	 
	 /** 
	  * Performing the Depth-first Search is done by calling:
	  * 
	  * list = test.printPath(String origin, String target, int depth, ArrayList<String> returnPath);
	  * 
	  * parameters:
	  *  origin: The source that we start at
	  *  target: The source that we are looking for
	  *  depth: how many levels deep we go before turning around
	  *  returnPath: an ArrayList that will hold String values of each respective source in the path
	  *  ** DONT FORGET TO MAKE AN ArrayList<String> to send in, and use this for the value of the path after it returns **
	  */
	 //ArrayList<String> path = new ArrayList<String>();
	 // path = test.printPath(originSrc, targetSrc, int depth, path);
  
 }// END MAIN
}// END CLASS


  /** USE THIS TO TEST IF NEEDED! */
/*
import java.util.ArrayList;


public class main {

	public static void main(String[] args) {
	Graph test = new Graph();

	test.addVert("OhMy!");
	test.addVert("OhNo!");
	test.addVert("TooSoon!");
	test.addVert("GoodDay!");
	test.addVert("SoSorry!");

	test.addEdge("OhMy!","OhNo!");
	test.addEdge("OhNo!", "OhMy!");
	test.addEdge("OhNo!", "TooSoon!");
	test.addEdge("GoodDay!", "TooSoon!");
	test.addEdge("OhNo!", "GoodDay!");
	test.addEdge("OhNo!", "SoSorry!");
	test.addEdge("OhMy!","SoSorry!");

	ArrayList t = test.matchString("Oh");
	System.out.println(t);

	ArrayList path = new ArrayList<String>();

	System.out.println("Vertices: "+test.getVerts()+" Edges: "+test.getEdges());

	test.printPath("OhMy!", "SoSorry!", 1, path);

	}// end main

}// end class
*/