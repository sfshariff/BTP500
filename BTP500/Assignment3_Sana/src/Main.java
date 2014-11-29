import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Stack;
import java.util.TreeSet;


public class Main {
	public Goal goal;
	public Requires requires;
	public Provided provided;
	public Created created;
	
	public Main() 
	{
		goal = new Goal();
		requires = new Requires();
		provided = new Provided();
		created = new Created();
		
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
	 
				goal.sources.add(source[0]);
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
	
	
	public void loadCSVFiles(ArrayList<String> list1, ArrayList<String> list2, String path) {
		 
		String csvFile = "CSVFiles/"+path;
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
	 
		try {
	 
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
	 
			        // use comma as separator
				String[] source = line.split(cvsSplitBy);
	 
				list1.add(source[0]);
				list2.add(source[1]);
	 
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
		loadCSVFiles(this.requires.source, this.requires.capability, "secondToCheck.csv");
		loadCSVFiles(this.provided.capability, this.provided.binary, "thirdToCheck.csv");
		loadCSVFiles(this.created.binary, this.created.source, "fourth_getSource.csv");
	}
	
	public static void main(String[] args) {
		Main object = new Main();	//creating a main object to load files and determine dependencies
		Graph graph = new Graph();	//creating a graph object to add vertices and edges
		String capabilities = null;
		String binaries = null;
		String source = null;
		boolean found = false;
		
		//loading csv files into the data structures
		object.loadFiles(graph);
		
		//iterator to iterate through the goal sources line by line
		Iterator iterator = object.goal.sources.iterator();
		
		//while the iterator has a next object
		while (iterator.hasNext())
		{
			//getting goal source line by line
			String source1 = iterator.next().toString();

			//checking the requires table for the source1
			for (int i = 0; i < object.requires.source.size(); i++)
			{	
				found = false;
				
				//if the source1 is found in requires table, fetching the required capability
				if (object.requires.source.get(i).toString().equals(source1))
				{
					
					capabilities = object.requires.capability.get(i).toString();
					
					//checking the provided table for the fetched capability
					for (int k = 0; k < object.provided.capability.size() && found == false; k++)
					{
						//if the capability found in the requires table, fetching the binary provided for the capability
						if (object.provided.capability.get(k).toString().equals(capabilities)==true)
						{
							
							binaries = object.provided.binary.get(k).toString();
							
							//checking the created table for the fetched binary to get the source2
							for (int j = 0; j < object.created.binary.size() && found == false; j++)
							{
								if (object.created.binary.get(j).toString().equals(binaries)==true)
								{
									source = object.created.source.get(j).toString();
									found = true;	//flag to true to exit the for loop
									
									//check if the source2 found is a goal source, and add an edge
									if(object.goal.sources.contains(source)) {
										graph.addEdge(source1, source);
																		
										
									}
								}
							}
						}
					}
				}
											
					
			}
		}
		System.out.println("DONE!!!");
		graph.printTree();
		boolean result = graph.depthFirst("kernel-3.17.0-301.fc21.src.rpm", "glibc-2.20-5.fc21.src.rpm", 2);
		System.out.println(result);
		
	}

}
