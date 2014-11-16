import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
/*
 * PURPOSE: A Java program to answer queries about software packages that are used to build a very big system.
 * 
 * build a graph of dependencies and answer various queries about chains of dependencies. In detail, this program will:
 *
 *	•	load in the tables (in Excel CSV format) 
 *	•	determine all (single step) dependencies, using only goal sources
 *	•	create a directed graph from the dependencies
 *	•	answer these type of queries:
 *		o	list all sources that match a simple string pattern
 *		o	list all cap’s of a goal source that match a simple string pattern
 *		o	find if there is a path between 2 given sources 
 */
	// Classes
	//==============
	
	// Binaries
	public static Binary[]		 bin;
	// Capbilities
	public static Capability[]	 cap;
	// Goals
	public static Goal[]		 goal;
	// Sources
	public static Source[]		 src;
	// Requires
	public static Require[]		 req;
	// Provided
	public static Provided[] 	 prov;
	// Created
	public static Created[]		 creat;
	
	// CSV
	//===============
	// CSV filepaths
	public static String _DELIM = ",";	// delimiter in the source csv (or otherwise) files
	
	// created	- The created table is a list of binaries that are created by sources
	private static String creaFilePath = "._creat.csv";
	final static File creaPath = new File (creaFilePath);
	
	// goal		- The goal table is a list of sources that comprise a Linux Distribution. NO DUPLICATES.
	private static String goalFilePath = "_goal.csv";
	final static File goalPath = new File ("goal.csv");
	
	// provided	- The provided table is a list of capabilities and associated binaries
	private static String provFilePath = "_prov.csv";
	final static File provPath = new File (provFilePath);
	
	// requires	- The requires table is a list of sources and associated required capabilities
	private static String reqFilePath = "_req.csv";
	final static File reqPath = new File (reqFilePath);
	
	// Scanner for CSV Purposes
	private static Scanner sc;
	
	
public static void main(String [] args) throws FileNotFoundException{
	
	// LOAD TABLES IN CSV FORMAT
	int c=0; // counter to populate array

	// Read Goals, populate array with values from CSV
	// src
	sc = new Scanner(goalPath);
	while (sc.hasNextLine() ){
		src[c] = new Source();
		src[c].data = sc.next();		
		c++;
	}
	
	// Read Required, populate array with values from CSV
	// src, cap  
	sc = new Scanner(reqPath);
	c=0;
	while (sc.hasNextLine() ){
		req[c].src = new Source();
		req[c].cap = new Capability();
		
		req[c].src.data = sc.next();
		req[c].cap.data = sc.next();
		c++;
	}	

	// Read Provided , populate array with values from CSV
	// cap, bin
	sc = new Scanner(provPath);
	c=0;
	while (sc.hasNextLine() ){
		prov[c].cap = new Capability();
		prov[c].bin = new Binary(); 
		
		prov[c].cap.data = sc.next();
		prov[c].bin.data = sc.next();
		c++;
	}
	
	// Read Created , populate array with values from CSV
	// bin, src
	sc = new Scanner(creaPath);
	c=0;
	while (sc.hasNextLine() ){
		creat[c].bin = new Binary();
		creat[c].src = new Source();
		
		creat[c].bin.data = sc.next();
		creat[c].src.data = sc.next();
		c++;
	}
	
	
	
	
	
	
	
	
	//TITLE
	System.out.println("=============================");
	System.out.println("|                           |");
	System.out.println("=============================");

	// PROMPT
	System.out.println("=============================");
	System.out.println("|                           |");
	System.out.println("=============================");
	
	// DETERMINE ALL SINGLE STEP DEPENDANCIES (GOALS ONLY)
	
	// CREATE DIRECTED GRAPH FROM DEPENDENCIES
	
	// QUERY HANDLING
	//	LIST ALL SOURCES MATCHING A SIMPLE STRING PATTERN
	//	LIST (IN ALL CAPS) A GOAL SOURCE THAT MATCHES A PATTERN STRING
	//	FIND A PATH BETWEEN 2 SOURCES
	
}// end main
}// end Class
