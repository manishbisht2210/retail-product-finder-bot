package org.outliers.retailproductfinderservice.util.pathfinder;

import org.outliers.retailproductfinderservice.objects.model.Bay;
import org.outliers.retailproductfinderservice.objects.model.BayEdge;
import org.outliers.retailproductfinderservice.objects.model.Graph;
import org.outliers.retailproductfinderservice.objects.model.GraphEdge;

public class GraphToDiGraphConverter {

	public static DiGraph convertGraph(Graph graph) {
		
		// create graph
		DiGraph diGraph = new DiGraph();

		for(GraphEdge edge: graph.getGraphEdges())
		{
			diGraph.add(edge.getStartGraphNode().getGraphNodeId(), edge.getEndGraphNode().getGraphNodeId(), Integer.valueOf(edge.getDistance()), edge.getOrientation().toString());
		}
		return diGraph;
	}
	
	public static DiGraph convertBayGraph(Bay bay) {
		
		// create graph
		DiGraph diGraph = new DiGraph();

		for(BayEdge edge: bay.getBayEdges())
		{
			diGraph.add(edge.getStartBayNode().getBayNodeId(), edge.getEndBayNode().getBayNodeId(), Integer.valueOf(edge.getDistance()), edge.getOrientation().toString());
		}
		return diGraph;
	}
}
