import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstras {
	
	/*
	 * This method allows a user to type in the file name, start vertex, and end vertex
	 * before running the algorithm and printing the shortest path.
	 */
	public void runShortestPathWithIO() throws FileNotFoundException{
		System.out.println("Enter the file name");
		Scanner scanner = new Scanner(System.in);
		String fileName = scanner.nextLine();
		GraphMaker maker = new GraphMaker();
		AdjListGraph graph = maker.makeGraphFromFile(fileName);
		graph.print();
		
		System.out.println("Enter the starting vertex");
		String start = scanner.nextLine();
		DijkstraVertex source = (DijkstraVertex)maker.getVertex(graph, start);
		System.out.println("Enter the ending vertex");
		String end = scanner.nextLine();
		DijkstraVertex destination = (DijkstraVertex)maker.getVertex(graph, end);
		
		HashMap<DijkstraVertex, DijkstraVertex> parent = shortestPath(graph,source);
		System.out.println("The shortest path is "+destination.getDistance());
		System.out.println(makePath(parent,source,destination));
	}
	
	/*
	 * This method takes as input the map from Vertex to Vertex representing the parent 
	 * mapping from Dijkstra's.  It assumes that Dijkstra's has already been run with the 
	 * given source index.  This method returns a string giving the path from source to 
	 * destination in the format VertexName::VertexName:: etc...
	 */
	public String makePath(HashMap<DijkstraVertex, DijkstraVertex> parentMap, DijkstraVertex source, DijkstraVertex destination){
		String path = "";
		while (destination != source) {
			path = "::" + destination.getName() + path;
			destination = parentMap.get(destination);
		}
		path = source.getName() + path;
		return path;
		// as long as current destination != source
	}

	/*
	 * This method does the work of Dijkstra's algorithm.  It creates and returns a map of
	 * Dijkstras Vertex to Dijkstra's Vertex, representing the mapping of each vertex to its parent.
	 * This map can be used to re-create the shortest paths from the source vertex to any other.
	 */
	public HashMap<DijkstraVertex, DijkstraVertex> shortestPath(AdjListGraph graph, DijkstraVertex source){
		
		PriorityQueue<DijkstraVertex> graphPQ = new PriorityQueue<DijkstraVertex>();
		HashMap<DijkstraVertex, DijkstraVertex> graphHashMap = new HashMap<DijkstraVertex, DijkstraVertex>();
		
		// Setting the distances to all of the vertices to Pos. infinity
		for (int i = 0; i < graph.numVertices(); i ++) {
			DijkstraVertex toBeAdded = (DijkstraVertex)(graph.getVertices().get(i));
			toBeAdded.setDistance(Double.POSITIVE_INFINITY);
			graphPQ.add(toBeAdded);
		}
		// Setting the distance to the source vertex equal to 0
		graphPQ.remove(source);
		source.setDistance(0.0);
		graphPQ.add(source);
		
		// Comparing distance values and swapping if we find a smaller value
		while (graphPQ.peek() != null) {
			// Pulling the head vertex off of the PQ
			DijkstraVertex toBeCompared = graphPQ.poll();
			// Checking all the children of the vertex distances
			for (Vertex childVertex: graph.getAdjacentVertices(toBeCompared)) {
				DijkstraVertex childVertexDijkstra = (DijkstraVertex) childVertex;
				if (childVertexDijkstra.getDistance() > (toBeCompared.getDistance() + toBeCompared.getEdgeWeight(childVertexDijkstra))) {
					childVertexDijkstra.setDistance((toBeCompared.getDistance() + toBeCompared.getEdgeWeight(childVertexDijkstra)));
					graphPQ.remove(childVertexDijkstra);
					graphPQ.add(childVertexDijkstra);
					graphHashMap.put(childVertexDijkstra, toBeCompared);
				}
			}
		}
		
		return graphHashMap;
	}
}
