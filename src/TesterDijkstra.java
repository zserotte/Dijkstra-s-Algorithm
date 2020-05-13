import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.HashMap;

import org.junit.Test;

public class TesterDijkstra {

	@Test
	public void testJapan() throws FileNotFoundException {
		GraphMaker gm = new GraphMaker();
		AdjListGraph graph = gm.makeGraphFromFile("JapanCities.txt");
		Dijkstras d= new Dijkstras();
		DijkstraVertex source = (DijkstraVertex)gm.getVertex(graph, "Takamatsu");
		DijkstraVertex end = (DijkstraVertex)gm.getVertex(graph, "Nakamura");
		HashMap<DijkstraVertex, DijkstraVertex> parent = d.shortestPath(graph,source);
		assertTrue(end.getDistance()==267.0);
		assertEquals(d.makePath(parent, source, end),"Takamatsu::Ikeda::Kochi::Nakamura");
		source = (DijkstraVertex)gm.getVertex(graph, "Tokushima");
		end = (DijkstraVertex)gm.getVertex(graph, "Uwajima");
		parent = d.shortestPath(graph,source);
		assertTrue(end.getDistance()==287.0);
		assertEquals(d.makePath(parent, source, end),"Tokushima::Ikeda::Kawanoe::Matsuyama::Uwajima");
		source = (DijkstraVertex)gm.getVertex(graph, "Ikeda");
		end = (DijkstraVertex)gm.getVertex(graph, "Takamatsu");
		parent = d.shortestPath(graph,source);
		assertTrue(end.getDistance()==57.0);
		assertEquals(d.makePath(parent, source, end),"Ikeda::Takamatsu");
		source = (DijkstraVertex)gm.getVertex(graph, "Ikeda");
		end = (DijkstraVertex)gm.getVertex(graph, "Nakamura");
		parent = d.shortestPath(graph,source);
		assertTrue(end.getDistance()==210.0);
		assertEquals(d.makePath(parent, source, end),"Ikeda::Kochi::Nakamura");
	}
	
	@Test
	public void testRouters() throws FileNotFoundException {
		GraphMaker gm = new GraphMaker();
		AdjListGraph graph = gm.makeGraphFromFile("Routers.txt");
		Dijkstras d= new Dijkstras();
		DijkstraVertex source = (DijkstraVertex)gm.getVertex(graph, "RTR1");
		DijkstraVertex end = (DijkstraVertex)gm.getVertex(graph, "RTR5");
		HashMap<DijkstraVertex, DijkstraVertex> parent = d.shortestPath(graph,source);
		assertTrue(end.getDistance()==0.605);
		assertEquals(d.makePath(parent, source, end),"RTR1::RTR4::RTR3::RTR5");
		source = (DijkstraVertex)gm.getVertex(graph, "RTR3");
		end = (DijkstraVertex)gm.getVertex(graph, "RTR4");
		parent = d.shortestPath(graph,source);
		assertTrue(end.getDistance()==0.31);
		assertEquals(d.makePath(parent, source, end),"RTR3::RTR5::RTR4");
	}
	
	@Test
	public void testCities() throws FileNotFoundException {
		GraphMaker gm = new GraphMaker();
		AdjListGraph graph = gm.makeGraphFromFile("Cities.txt");
		Dijkstras d= new Dijkstras();
		DijkstraVertex source = (DijkstraVertex)gm.getVertex(graph, "Shep");
		DijkstraVertex end = (DijkstraVertex)gm.getVertex(graph, "Rod");
		HashMap<DijkstraVertex, DijkstraVertex> parent = d.shortestPath(graph,source);
		assertTrue(end.getDistance()==60.0);
		assertEquals(d.makePath(parent, source, end),"Shep::Dwar::Puppi::Rod");
		source = (DijkstraVertex)gm.getVertex(graph, "Shep");
		end = (DijkstraVertex)gm.getVertex(graph, "Puppi");
		parent = d.shortestPath(graph,source);
		assertTrue(end.getDistance()==50.0);
		assertEquals(d.makePath(parent, source, end),"Shep::Dwar::Puppi");
	}
}
