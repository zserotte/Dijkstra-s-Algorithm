/*
 * This object represents a Vertex within a graph.
 * Each edge to other vertices is kept in the Vertex object as
 * an adjacency list.  A hashmap of weights is maintained if the 
 * graph is weighted.
 */
import java.util.*;

public class Vertex{
	private String myName;
	private HashMap<Vertex, Double> myEdges;
	// is the distance so far from the source

	public Vertex(String name){
		myName = name;
		myEdges = new HashMap<Vertex, Double>();
	}

	public String getName(){
		return myName;
	}
	
	// If a vertex is added with no weight, assume its weight is 1.
	public void addEdge(Vertex j){
		myEdges.put(j,1.0);
	}

	public void addEdge(Vertex j, Double weight){
		myEdges.put(j, weight);
	}
	
	public void removeEdge(Vertex j){
		myEdges.remove(j);
	}
	
	public boolean hasEdge(Vertex j){
		return myEdges.containsKey(j);
	}
	
	public double getEdgeWeight(Vertex j){
		if (myEdges.containsKey(j)){
			return myEdges.get(j);
		}
		else return Double.POSITIVE_INFINITY;
	}
	
	public Collection<Vertex> getAdjacentVertices(){
		return myEdges.keySet();
	}
	
	public boolean equals(Vertex v){
		if(!v.getName().equals(myName)) {
			System.out.println("1");
			return false;
		}
		Collection<Vertex> adjacents = v.getAdjacentVertices();
		if(adjacents.size()!=myEdges.keySet().size()){
			System.out.println("2");
			return false;
		}
		for(Vertex adj: adjacents){
			Vertex found = null;
			for(Vertex otherAdj:myEdges.keySet()){
				if(otherAdj.getName().equals(adj.getName())){
					found = otherAdj;
				}
			}
			if(found == null) return false;
			if(myEdges.get(found) != v.getEdgeWeight(adj)) {
				System.out.println("4");
				return false;
			}
		}
		//if(!adjacents.containsAll(myEdges.keySet())) return false;
		//if(!myEdges.keySet().containsAll(adjacents)) return false;
		return true;
	}
	
	public String toString(){
		String answer = this.myName+ ": ";
		for(Vertex v:this.myEdges.keySet()){
			answer += v.getName()+" ("+this.getEdgeWeight(v)+") ";
		}
		return answer;
	}
}
