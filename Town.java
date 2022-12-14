
import java.util.ArrayList;
/**
 * 
 * @author Jusu Diukuly
 *
 */

public class Town implements Comparable<Town>{
	private String name;
	private ArrayList<Town> townsVariable;
	
	/**
	 *To get the town's name
	 * @param name the name of this Town
	 */
	public Town(String name) {
		this.name = name;
		townsVariable = new ArrayList<Town>();
	}
	
	/**
	 * The copy constructor 
	 * @param templateTown The town to be copied
	 */
	public Town(Town templateTown) {
		this.name = templateTown.name;
		
		this.townsVariable = new ArrayList<Town>();
		
		for(Town t: templateTown.townsVariable) {
			this.townsVariable.add(t);
		}
	}
	
	/**
	 * @return 0 if names are equal, positive or 
	 * negative integer if names are not equal
	 */
	@Override
	public int compareTo(Town t) {
		return name.compareTo(t.name);
	}
	
	/**
	 * To get the name of the town
	 * @return the Town's name
	 */
	@Override
	public String toString() {
		return name;
	}
	
	/**
	 * @return the hashcode of the string
	 */
	@Override
	public int hashCode() {
		return name.hashCode();
	}
	
	/**
	 * @return if this Town's name is equal 
	 * to the given Town's name
	 */
	@Override
	public boolean equals(Object r) {
	Town town=(Town)r;
		return this.name.equals(town.getName());
			
	}
	
	/**
	 * Get the Town's name
	 * @return the name of the road
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * To add town to the list of towns if not already present
	 * @param t The town to be added
	 */
	
	public void addFirstAdjacentTown(Town town) {
		if(townsVariable.contains(town))
			return;
		townsVariable.add(town);
	}
	
	/**
	 * To remove a town from the list of towns
	 * @param t town to be removed
	 */
	public void removefirstAdjacentTown(Town town) {
		townsVariable.remove(town);
	}

	/**
	 * To return true if the given town is within the the listed town
	 * @param t to check for the town
	 * @return t if adjacent
	 */
	public boolean itIsAdjacentTo(Town town) {
		return townsVariable.contains(town);
	}
	
	
}