
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
/**
 * The TownGraphManager implements the 
 * TownGraphManagerInterface, in which edge the of the town, 
 * same town, and adjacent towns are printed
 * @author Jusu Dukuly
 *
 */
public class TownGraphManager implements TownGraphManagerInterface {
	private Graph graphVariable;
	public TownGraphManager() {
	graphVariable=new Graph();
	}
	@Override
	public boolean addRoad(String town1, String town2, int weight, String roadName) {

		if (graphVariable.addEdge(new Town(town1), new Town(town2), weight, roadName) == null)
			return false;

		return true;

	}
	@Override
	public Town getTown(String name) {
		return graphVariable.getVertex(new Town(name));
	}

	@Override
	public String getRoad(String town1, String town2) {
		return graphVariable.getEdge(new Town(town1), new Town(town2)).getName();
	}

	@Override
	public boolean addTown(String v) {
		return graphVariable.addVertex(new Town(v));
	}
	@Override
	public boolean containsTown(String v) {
	return (!graphVariable.containsVertex(new Town(v)))?false:true;
	
	}

	@Override
	public boolean containsRoadConnection(String town1, String town2) {
		return graphVariable.containsEdge(new Town(town1), new Town(town2));
	}

	@Override
	public ArrayList<String> allRoads() {
		ArrayList<String> roadList = new ArrayList<String>();

		for (Road road : graphVariable.edgeSet()) {
			roadList.add(road.getName());
		}
		Collections.sort(roadList);
		return roadList;
	}

	@Override
	public boolean deleteRoadConnection(String town1, String town2, String road) {
		if (!(graphVariable.removeEdge(new Town(town1), new Town(town2), 0, road) != null))
			return false;

		return true;
	}
	public void populateTownGraph(File selectedFile) throws FileNotFoundException, IOException {
		String[] getTokens;
		Scanner sc = new Scanner(selectedFile);
		while (sc.hasNextLine()) {
			String curr = sc.nextLine();
			getTokens = curr.split(";|,");
			graphVariable.addVertex(new Town(getTokens[2]));
			graphVariable.addVertex(new Town(getTokens[3]));
			graphVariable.addEdge(new Town(getTokens[2]), new Town(getTokens[3]), Integer.parseInt(getTokens[1]), getTokens[0]);
		} 
		
		sc.close();;

	}
	@Override
	public boolean deleteTown(String v) {
		return graphVariable.removeVertex(new Town(v));
	}

	@Override
	public ArrayList<String> allTowns() {
		ArrayList<String>townList = new ArrayList<String>();

		for (Town town : graphVariable.vertexSet()) {
			townList.add(town.getName());
		}
		Collections.sort(townList);

		return townList;
	}
	
	@Override
	public ArrayList<String> getPath(String town1, String town2) {
		return graphVariable.shortestPath(new Town(town1), new Town(town2));
	}

	
}