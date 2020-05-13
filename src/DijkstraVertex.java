
// Author: Zach Serotte

public class DijkstraVertex extends Vertex implements Comparable<DijkstraVertex> {
	double distance;
	// Value to hold distances

	public DijkstraVertex(String name) {
		super(name);
		// TODO Auto-generated constructor stub
	}

	// Method below compares the current distance to the new one
	@Override
	public int compareTo(DijkstraVertex o) {
		// Integer to return based on comparison result
		int comparisonResult;
		if (this.distance > o.distance) {
			comparisonResult = 1;
		}
		else if (this.distance < o.distance) {
			comparisonResult = -1;
		}
		else {
			comparisonResult = 0;
		}
		return comparisonResult;
	}
	
	public double getDistance(){
		return this.distance;
	}
	
	public void setDistance(Double pathDistance) {
		this.distance = pathDistance;
	}
	
}
