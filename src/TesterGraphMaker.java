import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class TesterGraphMaker {
	
	private void matches(ArrayList<Vertex> vertices, ArrayList<Vertex> realVertices){
		for(Vertex v: realVertices){
			boolean found = false;
			for(Vertex u: vertices){
				if(v.equals(u)) found = true;
			}
			if(!found){
				System.out.println("Couldn't match "+v.getName());
			}
			assertTrue(found);
		}
		for(Vertex v: vertices){
			boolean found = false;
			for(Vertex u: realVertices){
				if(v.equals(u)) found = true;
			}
			if(!found){
				System.out.println("Couldn't match "+v.getName());
			}
			assertTrue(found);
		}
	}
	
	@Test
	public void testCities() throws FileNotFoundException{
		GraphMaker gm = new GraphMaker();
		AdjListGraph graph = gm.makeGraphFromFile("Cities.txt");
		assertTrue(graph.numEdges()==7);
		assertTrue(graph.numVertices()==5);
		ArrayList<Vertex> vertices = graph.getVertices();
		ArrayList<Vertex> realVertices = new ArrayList<Vertex>();
		Vertex s = new DijkstraVertex("Shep");
		Vertex c = new DijkstraVertex("Cats");
		Vertex p = new DijkstraVertex("Puppi");
		Vertex d = new DijkstraVertex("Dwar");
		Vertex r = new DijkstraVertex("Rod");
		s.addEdge(d, 30.0);
		s.addEdge(c, 10.0);
		s.addEdge(r, 100.0);
		c.addEdge(p, 50.0);
		p.addEdge(r, 10.0);
		d.addEdge(r, 60.0);
		d.addEdge(p, 20.0);
		realVertices.addAll(Arrays.asList(new Vertex[]{s, c, p, d, r}));
		matches(vertices, realVertices);
		
	}
	
	@Test
	public void testRouters() throws FileNotFoundException{
		GraphMaker gm = new GraphMaker();
		AdjListGraph graph = gm.makeGraphFromFile("Routers.txt");
		assertTrue(graph.numEdges()==14);
		assertTrue(graph.numVertices()==5);
		ArrayList<Vertex> vertices = graph.getVertices();
		ArrayList<Vertex> realVertices = new ArrayList<Vertex>();
		Vertex rtr1 = new DijkstraVertex("RTR1");
		Vertex rtr2 = new DijkstraVertex("RTR2");
		Vertex rtr3 = new DijkstraVertex("RTR3");
		Vertex rtr4 = new DijkstraVertex("RTR4");
		Vertex rtr5 = new DijkstraVertex("RTR5");
		rtr1.addEdge(rtr4, 0.302);
		rtr1.addEdge(rtr2, 0.105);
		rtr1.addEdge(rtr5, 1.0);
		rtr2.addEdge(rtr3, 0.5);
		rtr2.addEdge(rtr1, 0.62);
		rtr3.addEdge(rtr3, 0.298);
		rtr3.addEdge(rtr5, 0.1);
		rtr3.addEdge(rtr1, 0.375);
		rtr4.addEdge(rtr4, 0.418);
		rtr4.addEdge(rtr3, 0.203);
		rtr4.addEdge(rtr5, 0.6);
		rtr5.addEdge(rtr4, 0.21);
		rtr5.addEdge(rtr2, 0.41);
		rtr5.addEdge(rtr1, 0.816);
		realVertices.addAll(Arrays.asList(new Vertex[]{rtr1, rtr2, rtr3, rtr4, rtr5}));
		matches(vertices, realVertices);
	}
	
	@Test
	public void testJapanCities() throws FileNotFoundException {
		GraphMaker gm = new GraphMaker();
		AdjListGraph graph = gm.makeGraphFromFile("JapanCities.txt");
		assertTrue(graph.numEdges()==30);
		assertTrue(graph.numVertices()==9);
		ArrayList<Vertex> vertices = graph.getVertices();
		ArrayList<Vertex> realVertices = new ArrayList<Vertex>();
		Vertex taka = new DijkstraVertex("Takamatsu");
		Vertex toku = new DijkstraVertex("Tokushima");
		Vertex ikeda = new DijkstraVertex("Ikeda");
		Vertex kaw = new DijkstraVertex("Kawanoe");
		Vertex kochi = new DijkstraVertex("Kochi");
		Vertex mur = new DijkstraVertex("Muroto");
		Vertex mat = new DijkstraVertex("Matsuyama");
		Vertex uwa = new DijkstraVertex("Uwajima");
		Vertex naka = new DijkstraVertex("Nakamura");
		taka.addEdge(kaw, 73.0);
		taka.addEdge(toku, 76.0);
		taka.addEdge(ikeda, 57.0);
		toku.addEdge(taka, 76.0);
		toku.addEdge(mur, 128.0);
		toku.addEdge(ikeda, 74.0);
		ikeda.addEdge(taka,57.0);
		ikeda.addEdge(kaw,26.0);
		ikeda.addEdge(kochi,82.0);
		ikeda.addEdge(toku,74.0);
		kaw.addEdge(taka,73.0);
		kaw.addEdge(kochi,87.0);
		kaw.addEdge(mat,95.0);
		kaw.addEdge(ikeda,26.0);
		kochi.addEdge(kaw,87.0);
		kochi.addEdge(mur,83.0);
		kochi.addEdge(naka,128.0);
		kochi.addEdge(mat,127.0);
		kochi.addEdge(ikeda,82.0);
		kochi.addEdge(uwa,148.0);
		mur.addEdge(kochi,83.0);
		mur.addEdge(toku,128.0);
		mat.addEdge(kaw,95.0);
		mat.addEdge(uwa,92.0);
		mat.addEdge(kochi,127.0);
		uwa.addEdge(kochi,148.0);
		uwa.addEdge(naka,87.0);
		uwa.addEdge(mat,92.0);
		naka.addEdge(kochi,128.0);
		naka.addEdge(uwa,87.0);
		//System.out.println(vertices);
		realVertices.addAll(Arrays.asList(new Vertex[]{taka, toku, ikeda, kaw, kochi, mur, mat, uwa, naka}));
		matches(vertices, realVertices);
	}
}
