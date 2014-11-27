import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;


public class Main {
	public TreeSet<String> goal;
	public Requires requires;
	public Provided provided;
	public Created created;
	
	public Main() 
	{
		goal = new TreeSet<String>();
		requires = new Requires();
		provided = new Provided();
		created = new Created();
		
	}
	
	
	public void readCSVGoals() {
		 
		String csvFile = "CSVFiles/lb_goal.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
	 
		try {
	 
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
	 
			        // use comma as separator
				String[] source = line.split(cvsSplitBy);
	 
				goal.add(source[0]);
	 
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
		this.readCSVRequires();
	  }
	
	public void readCSVRequires() {
		 
		String csvFile = "CSVFiles/secondToCheck.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
	 
		try {
	 
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
	 
			        // use comma as separator
				String[] source = line.split(cvsSplitBy);
	 
				this.requires.source.add(source[0]);
				this.requires.capability.add(source[1]);
	 
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
		this.readCSVProvided();
	  }
	
	public void readCSVProvided() {
		 
		String csvFile = "CSVFiles/thirdToCheck.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
	 
		try {
	 
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
	 
			        // use comma as separator
				String[] source = line.split(cvsSplitBy);
	 
				this.provided.capability.add(source[0]);
				this.provided.binary.add(source[1]);
	 
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
		this.readCSVCreated();
	  }
	
	public void readCSVCreated() {
		 
		String csvFile = "CSVFiles/fourth_getSource.csv";
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
	 
		try {
	 
			br = new BufferedReader(new FileReader(csvFile));
			while ((line = br.readLine()) != null) {
	 
			        // use comma as separator
				String[] source = line.split(cvsSplitBy);
	 
				this.created.binary.add(source[0]);
				this.created.source.add(source[1]);
	 
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
	
	public static void main(String[] args) {
		Main object = new Main();
		Graph graph = new Graph();
		String capabilities = null;
		String binaries = null;
		String source = null;
		boolean found = false;
		int count =0;
		
		object.readCSVGoals();
		
		//System.out.println(object.goal);
		
		Iterator iterator = object.goal.iterator();
		
		while (iterator.hasNext())
		{
			count++;
			String string = iterator.next().toString();
			//System.out.println("count: "+count);
			graph.addVert(string);

			
			for (int i = 0; i < object.requires.source.size(); i++)
			{
				found = false;
				//System.out.println("size" + object.requires.source.size());
				//System.out.println("String : "+ string);
				
				//System.out.println("Source : " + object.requires.source.get(i));
				//System.out.println(string.equals(object.requires.source.get(i)));
				
				if (object.requires.source.get(i).toString().equals(string))
				{
					capabilities = object.requires.capability.get(i).toString();
					//System.out.println("Capabilities: "+capabilities );
					
					for (int k = 0; k < object.provided.capability.size() && found == false; k++)
					{
						//System.out.println("inside capability");
						if (object.provided.capability.get(k).toString().equals(capabilities)==true)
						{
							binaries = object.provided.binary.get(k).toString();
							
							for (int j = 0; j < object.created.binary.size() && found == false; j++)
							{
								if (object.created.binary.get(j).toString().equals(binaries)==true)
								{
									source = object.created.source.get(j).toString();
									found = true;
									
									if(object.goal.contains(source))
										graph.addEdge(string, source);
									//System.out.println("done!!!!");
								}
							}
						}
					}
				}
											
					
			}
		}
        //testing
		System.out.println("DONE!!!");
		//graph.printTree();
		
		
	}

}
