import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Map.Entry;


public class Main {
	public TreeSet<String> sources;
	public TreeMap<String, TreeSet<String>> goal;
	public TreeMap<String, TreeSet<String>> requires;
	public TreeMap<String, TreeSet<String>> provided;
	public TreeMap<String, TreeSet<String>> created;
	
	public Main() 
	{
		sources = new TreeSet<String>();
		goal = new TreeMap<String, TreeSet<String>>();		
		requires = new TreeMap<String, TreeSet<String>>();	
		provided = new TreeMap<String, TreeSet<String>>();	
		created = new TreeMap<String, TreeSet<String>>();	
		
	}
	
	
	public void readCSVGoals(Graph graph) {
		 
		String csvFile = "CSVFiles/lb_goal.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
	 
		try {
	 
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
	 
			        // use comma as separator
				String[] source = line.split(cvsSplitBy);
	 
				sources.add(source[0]);
				graph.addVert(source[0]);
	 
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//testing
		System.out.println("Done");
	  }
	
	
	public void loadCSVFiles(TreeMap<String, TreeSet<String>> list, String path) {
		 
		String csvFile = "CSVFiles/"+path;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
	 
		try {
	 
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
	 
			        // use comma as separator
				String[] source = line.split(cvsSplitBy);
				
				if(!list.containsKey(source[0])) 
				{
					list.put(source[0], new TreeSet<String>());
					list.get(source[0]).add(source[1]);
				}
				
				else
					list.get(source[0]).add(source[1]);
				
	 
			}
	 
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		//testing
		System.out.println("Done");
	  }
	
	public void loadFiles(Graph graph)
	{
		readCSVGoals(graph);
		loadCSVFiles(this.requires,"secondToCheck.csv");
		loadCSVFiles(this.provided, "thirdToCheck.csv");
		loadCSVFiles(this.created, "fourth_getSource.csv");
	}
	
	public static void main(String[] args) {
		Main object = new Main();	//creating a main object to load files and determine dependencies
		Graph graph = new Graph();	//creating a graph object to add vertices and edges
		String capabilities = null;
		String binaries = null;
		String source2 = null;
		
		//loading csv files into the data structures
		object.loadFiles(graph);
		
		Iterator iterator = object.sources.iterator();
		
		//while the iterator has a next object
		while (iterator.hasNext())
		{
			//getting goal source line by line
			String source1 = iterator.next().toString();
			
			//checking requires table for the goal source
			if(object.requires.containsKey(source1))
			{
				//if the source1 is found in requires table, fetching the required capability
				for(Iterator it_req = object.requires.get(source1).iterator(); it_req.hasNext();)
				{
					capabilities = it_req.next().toString();
					
					//checking the provided table for the fetched capability
					//if the capability found in the requires table, fetching the binary provided for the capability
					for(Iterator it_prov = object.provided.get(capabilities).iterator(); it_prov.hasNext();)
					{
						binaries = it_prov.next().toString();
						
						//checking the created table for the fetched binary to get the source2
						for(Iterator it = object.created.get(binaries).iterator(); it.hasNext();)
						{
							source2 = it.next().toString();
							
							//check if the source2 found is a goal source, and add an edge
							if(object.sources.contains(source2))
								graph.addEdge(source1, source2);
						}
					}
					
				}
			}
			
		}
		
		
		//while the iterator has a next object
		
		System.out.println("DONE!!!");
		//graph.printTree();
		//boolean result = graph.depthFirst("kernel-3.17.0-301.fc21.src.rpm", "glibc-2.20-5.fc21.src.rpm", 2);
		//System.out.println(result);
		
	}

}
