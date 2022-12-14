
/**
 * 
 * @author Jusu Dukuly
 *
 */
public class Road implements Comparable<Road> {
	private int degrees_Weight;
	private Town source, destination_Area;
	private String _NAME_;
	/**
	 * @param -source of the town
	 * @param -destination Another town on the road
	 * @param -degrees distance from one town to another
	 * @param -name of the road
	 */
	public Road(Town source, Town destination, int degrees, String name) {
		this.source = new Town(source);
		this.destination_Area = new Town(destination);

		this._NAME_ = name;
		this.degrees_Weight = degrees;
	}

	/**
	 * Constructor with preset of 1.
	 * @param -source town on the road
	 * @param -destination town on the road
	 * @param -name of the road
	 */
	public Road(Town source, Town destination, String name) {
		this.source = new Town(source);
		this.destination_Area = new Town(destination);
		this._NAME_ = name;
		this.degrees_Weight = 1;
	}
	/**
	 * @return to print out string representation
	 * of road class
	 */
	@Override
	public String toString() {
		return source.getName() + " via " + _NAME_ + " to " +destination_Area.getName() + " " + degrees_Weight + " mi";
	}
	/**
	 * @return 0 if the road names are the same, positive 
	 * or negative number if road names are not the same
	 */
	@Override
	public int compareTo(Road o) {
		return this._NAME_.compareTo(o._NAME_);
	}

	/**
	 *To show whether the road contains the specified town
	 * @param -town the vertex of the graph
	 * @return true if the edge is connected to the given vertex
	 */
	public boolean containsTown(Town town) {
		return this.source.equals(town) || this.destination_Area.equals(town);
	}
	/**
	 * To get the name of the road
	 * @return the name of this road
	 */
	public String getName() {
		return _NAME_;
	}
	/**
	 * Get the destination of town
	 * @return the destination town
	 */
	public Town getDestination() {
		return destination_Area;
	}

	/**
	 * Get the source of the town
	 * @return the source town
	 */
	public Town getSource() {
		return source;
	}

	/**
	 * Get the weight of the map
	 * @return the edge weight
	 */
	public int getWeight() {
		return degrees_Weight;
	}

	/**
	 * @return true if the ends of this road are the 
	 * same as the ends of the given road
	 */
	@Override
	public boolean equals(Object r) {
		Road road = (Road) r;
		return (this.source.equals(road.source) && this.destination_Area.equals(road.destination_Area))
				|| (this.source.equals(road.destination_Area) && this.destination_Area.equals(road.source));
	}
	
	/**
	 * @return the hashCode of the road name (String)
	 */
	@Override
	public int hashCode() {
		return _NAME_.hashCode();
	}

}