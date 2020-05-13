import java.io.File;
import java.io.FileNotFoundException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class GraphMaker {
	/*
	 * Create and return the Graph object representing the vertices and edges given in the file.
	 */
	public AdjListGraph makeGraphFromFile(String fileName) throws FileNotFoundException{
		
		File file = new File(fileName);
		Scanner scnr = new Scanner(file);
	
		int numCities;
		double distance;
		// Creating a graph to represent the vertices
		AdjListGraph vertexGraph = new AdjListGraph(true);
		
		numCities = scnr.nextInt();
		
		for (int i = 0; i < numCities; i++) {
			vertexGraph.addVertex(new DijkstraVertex(scnr.next()));
		}
		
		scnr.nextLine();
		
			for (int i = 0; i < numCities; i++) {
				scnr.next();
				for (int j = 0; j < numCities; j++) {
					distance = scnr.nextDouble();
					if (distance != 0) {
						vertexGraph.addEdge(vertexGraph.getVertices().get(i), vertexGraph.getVertices().get(j), distance);
					}
				}
			}
			return vertexGraph;
		} 

	/*
	 * Given a graph and a vertex name, return the corresponding vertex object.
	 */
	public Vertex getVertex(AdjListGraph graph, String name){
		ArrayList<Vertex> verticesInGraph = new ArrayList<Vertex>();
		verticesInGraph = graph.getVertices();
		Vertex contains;
		contains = null;
		for (int i = 0; i < verticesInGraph.size(); i++) {
			if (verticesInGraph.get(i).getName().equals(name)) {
				contains = verticesInGraph.get(i);
			}
		}
		return contains;
	}
}
