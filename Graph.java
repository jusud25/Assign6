import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Vector;
/**
 * @author Jusu Dukuly
 */
public class Graph implements GraphInterface<Town, Road> {
	private Map<String, Town> townPath;
	private HashSet<Road> roadNetworks;
	private Map<Town, ArrayList<String>> paths;
	private Map<Town, Integer> dist;
	private final int thisInfinity = -2;
	/**
	 * Constructor
	 */
	public Graph() {
		townPath = new HashMap<String, Town>();
		roadNetworks = new HashSet<Road>();
	}

	@Override
	public Road getEdge(Town sourceVertex, Town destinationVertex) {
		if (sourceVertex == null || destinationVertex == null)
			return null;

		if(!(containsVertex(sourceVertex) || containsVertex(destinationVertex)))
			return null;
		
		for (Road road : edgesOf(sourceVertex)) {
			if (road.getDestination().equals(destinationVertex))
				return road;
		}
		return null;
	
	}
	

	@Override
	public Road removeEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		boolean NotAvailable = true;
		
		if(sourceVertex == null && destinationVertex == null)
			return null;
		
		Road rem = new Road(sourceVertex, destinationVertex, weight, description);

		if (roadNetworks.remove(rem)) {

			for (Road rd : roadNetworks) {
				if (rd.containsTown(sourceVertex) && rd.containsTown(destinationVertex))
					NotAvailable = false;
	         } 

			if (NotAvailable) {
				townPath.get(sourceVertex.getName()).addFirstAdjacentTown(destinationVertex);
				townPath.get(destinationVertex.getName()).removefirstAdjacentTown(destinationVertex);
			}
		} 

		return null;
	}
	/**
	 * @param vertex Town to get
	 * @return vertex or null 
	 */
	public Town getVertex(Town vertex) {
		return townPath.get(vertex.getName());
	}

	@Override
	public Road addEdge(Town sourceVertex, Town destinationVertex, int weight, String description) {
		Road findNewRoad=null;

		if (sourceVertex == null || destinationVertex == null)
			throw new NullPointerException();

		if (!(containsVertex(sourceVertex) || containsVertex(destinationVertex)))
			throw new IllegalArgumentException();

		findNewRoad = new Road(sourceVertex, destinationVertex, weight, description);
		townPath.get(sourceVertex.getName()).addFirstAdjacentTown(destinationVertex);
		townPath.get(destinationVertex.getName()).addFirstAdjacentTown(destinationVertex);
		roadNetworks.add(findNewRoad);
		return findNewRoad;
	}

	@Override
	public boolean addVertex(Town v) {
		if (v == null)
			throw new NullPointerException();
		if (containsVertex(v))
			return false;
		townPath.put(v.getName(), new Town(v));

		return true;
	}

	@Override
	public boolean containsEdge(Town sourceVertex, Town destinationVertex) {
		if (!containsVertex(sourceVertex) || !containsVertex(destinationVertex))
			return false;

		return townPath.get(sourceVertex.getName()).itIsAdjacentTo(destinationVertex);
	}

	@Override
	public boolean containsVertex(Town v) {
		if(v == null)
			return false;
		
		return townPath.containsKey(v.getName());
	}

	@Override
	public Set<Road> edgeSet() {
		return roadNetworks;
	}

	@Override
	public Set<Road> edgesOf(Town vertex) {
		Set<Road> adjacencies;
		if (vertex == null)
			throw new NullPointerException();

		if (!containsVertex(vertex))
			throw new IllegalArgumentException();

		adjacencies = new HashSet<Road>();

		for (Road road : roadNetworks) {
			if (road.getSource().equals(vertex))
				adjacencies.add(road);
			else if (road.getDestination().equals(vertex))
				adjacencies.add(new Road(vertex, road.getSource(), road.getWeight(), road.getName()));
		} 

		return adjacencies;
	}

	@Override
	public boolean removeVertex(Town v) {
		Road cur;
		if (!containsVertex(v))
			return false;

		Iterator<Road> iterator = roadNetworks.iterator();
		while (iterator.hasNext()) {
			cur = iterator.next();

			if (cur.containsTown(v))
				iterator.remove();
		} 
		townPath.remove(v.getName());
		return true;
	}
	@Override
	public Set<Town> vertexSet() {
		Set<Town> vertex = new HashSet<Town>();
	  townPath.forEach((set, vertext)->vertex.add(vertext));
	    return vertex;
	}

	@Override
	public ArrayList<String> shortestPath(Town sourceVertex, Town destinationVertex) {
		dijkstraShortestPath(sourceVertex);
		if (!(dist.get(destinationVertex) != thisInfinity)) {
			return new ArrayList<String>();
		}
		return paths.get(destinationVertex);
	}

	@Override
	public void dijkstraShortestPath(Town sourceVertex) {
		HashMap<Town, Boolean> visited = new HashMap<Town, Boolean>();
																		
		dist = new HashMap<Town, Integer>();
		paths = new HashMap<Town, ArrayList<String>>();
		PriorityQueue<Vector2D> priority = new PriorityQueue<Vector2D>();
		Vector2D town;
		int distance;
		for (Map.Entry<String, Town> ent : townPath.entrySet()) {
			visited.put(ent.getValue(), false);
			dist.put(ent.getValue(), thisInfinity);
		}
		dist.put(sourceVertex, 0);
		priority.add(new Vector2D(sourceVertex, 0));
		while (!priority.isEmpty()) {
			town = priority.poll();
			visited.replace(town.t, true);
			if (dist.get(town.t) != thisInfinity && dist.get(town.t) < town.dis)
				continue;
			for (Road newerRoad : edgesOf(town.t)) {

				if (visited.get(newerRoad.getDestination()) == true)
					continue;
				distance = dist.get(town.t) + newerRoad.getWeight();

				if (distance < dist.get(newerRoad.getDestination()) || dist.get(newerRoad.getDestination()) == thisInfinity) {
					dist.replace(newerRoad.getDestination(), distance);
					priority.add(new Vector2D(newerRoad.getDestination(), distance));
					if (paths.get(town.t) == null) {
						paths.put(newerRoad.getDestination(), new ArrayList<String>());
					} else {
						paths.put(newerRoad.getDestination(),
								new ArrayList<String>(paths.get(town.t)));
					}
					paths.get(newerRoad.getDestination()).add(newerRoad.toString());
				} 
			} 
		} 
	}
	private class Vector2D implements Comparable<Vector2D> {
		Town t;
		int dis;
		/**
		 * @param town the town data
		 * @param distance the in distance
		 */
		@SuppressWarnings("unused")
		public Vector2D(Town town, int dis) {
			this.t = town;
			this.dis = dis;
		}
		/**
		 * @return v.dist
		 */
		@Override
		public int compareTo(Vector2D vector) {
			return vector.dis;
		
	}
	
}
}
