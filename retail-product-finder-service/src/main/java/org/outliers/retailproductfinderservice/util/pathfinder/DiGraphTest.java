package org.outliers.retailproductfinderservice.util.pathfinder;

import org.outliers.retailproductfinderservice.objects.model.Orientation;

public class DiGraphTest
{
	public static void main(String[] args)
	{

		// create graph
		DiGraph graph = new DiGraph();

		// add a bunch of edges
		/*graph.add("A", "B", 10, "E");
		graph.add("A", "F1", 18, "S");
		graph.add("B", "F", 15, "E");
		graph.add("B", "D", 12, "S");
		graph.add("D", "E", 2, "E"); // edge already exists!!!
		graph.add("F", "E", 5, "S");
		graph.add("F1", "C", 10, "E");
		graph.add("C", "F2", 15, "E");
		graph.add("F2", "E", 3, "N");*/

		graph.add("A", "B", 5, "E");
		graph.add("B", "F", 8, "E");
		graph.add("B", "D", 6, "S");
		graph.add("A", "F1", 9, "S");
		graph.add("lays", "F2", 3, "S");
		graph.add("F2", "C", 2, "W");
		graph.add("F1", "C", 5, "E");
		graph.add("D", "lays", 3, "E");

		System.out.println("Graph is connected: " + graph.DepthFirstSearch());
		System.out.println("Connected from LAX:" + graph.BreadthFirstSearch("LAX"));
		System.out.println();

		System.out.println(graph);
		System.out.println(graph.edgesToString());

		System.out.println("A to D");
		Result result = graph.getPath("A", "D", Orientation.N);
		for (Instruction each : result.getInstructions()) 
			System.out.println(each.toString());

		System.out.println("\nA to E");
		Result result1 = graph.getPath("A", "lays", Orientation.N);
		for (Instruction each : result1.getInstructions()) 
			System.out.println(each.toString());
		

		/*System.out.println("Adding Edge Las Vegas to Phoenix at cost $120");
		graph.add("LAS VEGAS", "PHOENIX", 120);

		System.out.println("\nSLC to PHOENIX");
		path = graph.getPath("SLC", "PHOENIX");
		for (String each : path) 
			System.out.println(each);

		System.out.println("\nSACRAMENTO to LAX");
		path = graph.getPath("SACRAMENTO", "LAX");
		for (String each : path) 
			System.out.println(each);

		System.out.println("\nSACRAMENTO to CHICAGO");
		path = graph.getPath("SACRAMENTO", "CHICAGO");
		for (String each : path) 
			System.out.println(each);*/
	}
}