
/**
 * Tree.java Data Structures CS-102, New York University
 * 
 * This class stores information about a particular tree that grows in New York
 * City. The class provides a nine argument constructor with appropriate getter
 * and setter methods. There is no default constructor. Overrides equals method
 * from Object. The class implement Comparable<> interface. All variables in
 * this class are tested for invalid arguments.
 * 
 * @author Alvaro Luken aluken@nyu.edu
 *
 **/

public class Tree implements Comparable<Tree> {
	private int tree_id;
	private int tree_dbh;
	private String status;
	private String health;
	private String spc_common;
	private int zipcode;
	private String boroname;
	private double x_sp;
	private double y_sp;

	public static int counter = 0; // just added for checking object creation

	/**
	 * Constructor: Creates a new Tree object with the specified params:
	 * 
	 * @param tree
	 *            id, diam, status, health, name, zipcode, borough name, x, and
	 *            y.
	 * @throws error
	 *             IllegalArgumentException for: id if input is negative, diam
	 *             if input is negative, name is null, zip is less than 0 or
	 *             greater than 100,000, borough name is empty or null
	 */
	public Tree(int id, int diam, String status, String health, String spc, int zip, String boro, double x, double y) {

		counter++; // increases by one for every Tree object created

		if (id < 0) {
			throw new IllegalArgumentException("Id must be a non-negative integer");
		}
		if (diam < 0) {
			throw new IllegalArgumentException("Diameter must be a non-negative integer");
		}

		if (spc.equals(null)) {
			throw new IllegalArgumentException("Name cannot be null");
		}
		if (zip <= 0 || zip > 99999) {
			throw new IllegalArgumentException("Zipcode must be positive and less than 100,000");
		}
		if (boro.equals("") || boro.equals(null)) {
			throw new IllegalArgumentException("Bourough name must be an actual borough, for example: \"Brooklyn\"");
		}

		this.tree_id = id;
		this.tree_dbh = diam;
		this.status = status;
		this.health = health;
		this.spc_common = spc;
		this.zipcode = zip;
		this.boroname = boro;
		this.x_sp = x;
		this.y_sp = y;

	}

	/**
	 * method that overrides the equals method in Object
	 * 
	 * @throws illegal
	 *             argument exception when creation of objects is tried with
	 *             same id
	 * @return true if Tree objects are equal, false if they are not equal
	 */
	@Override
	public boolean equals(Object obj) throws IllegalArgumentException {
		Tree other = (Tree) obj; // create an object "other" that inherits the
									// Tree class SHOULD I DO THIS??

		if (this.getTreeId() == other.getTreeId() && (this.getName() != other.getName())) {
			throw new IllegalArgumentException(
					"There cannot be any possibility of creating two Tree objects with identical id's but different species names.");

		}

		if (this.getTreeId() == other.getTreeId() && (this.getName() == other.getName())) {
			return true; // tree objects are equal
		}

		return false; // tree objects are not equal
	}

	/**
	 * Overrides interface's compareTo method
	 * 
	 * @param an
	 *            object of the same type as Tree
	 * @return 0 if objects are equal, -1 if argument object is less, or 1 if
	 *         argument object is greater
	 */
	@Override
	public int compareTo(Tree obj) { // overrides compareTo methhod from Object

		int nameDiff = this.getName().compareToIgnoreCase(obj.getName()); // will
																			// give
																			// either
																			// 0,
																			// 1,
																			// or
																			// -1

		if (nameDiff == 0) { // objects are equal
			if (this.getTreeId() > obj.getTreeId()) {
				return -1; // obj is less than
			} else {
				return 1; // obj is greater
			}
		}

		return 0; // objects are exactly equal --> should be an unreachable
					// case: error is checked earlier
	}

	/**
	 * Overrides Object's toString method
	 * 
	 * @return Tree object formatted
	 */
	@Override
	public String toString() {
		return String.format("%1$-30s %2$10s %3$10s %4$10s %5$10s %6$10s %7$10s %8$10s %9$10s", "Name: " + spc_common,
				"id: " + tree_id + " --", "diam: " + tree_dbh + " --", "status: " + status + " --",
				"health: " + health + " --", "zipcode: " + zipcode + " --", "borough: " + boroname + " --", x_sp,
				y_sp + " +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");

	}

	/**
	 * Sets the tree's id from data
	 */
	private void setTreeId(int id) {
		this.tree_id = id;
	}

	/**
	 * Gets the id of a Tree object
	 * 
	 * @return Tree object id
	 */
	public int getTreeId() {
		return this.tree_id;
	}

	/**
	 * Sets a Tree object with a name from data
	 * 
	 * @param Tree
	 *            object name from data
	 */
	private void setName(String spc) {
		this.spc_common = spc;
	}

	/**
	 * Gets a Tree object's name
	 * 
	 * @return name of Tree object
	 */
	public String getName() {
		return this.spc_common;
	}

	/**
	 * Sets a Tree object's diam
	 * 
	 * @param diam
	 *            from data
	 */
	private void setDiam(int diam) {
		this.tree_dbh = diam;
	}

	/**
	 * Gets a Tree object's diam
	 * 
	 * @return
	 */
	public int getDiam() {
		return this.tree_dbh;
	}

	/**
	 * Sets a Tree object's status
	 * 
	 * @param status
	 *            from data file
	 */
	private void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Gets a Tree object's status
	 * 
	 * @return status of tree
	 */
	public String getStatus() {
		return this.status;
	}

	/**
	 * Sets a Tree object's health
	 * 
	 * @param health
	 *            of tree
	 */
	private void setHealth(String health) {
		this.health = health;
	}

	/**
	 * Gets a Tree object's health
	 * 
	 * @return health of tree
	 */
	public String getHealth() {
		return this.health;
	}

	/**
	 * Sets a Tree object's zipcode
	 * 
	 * @param zipcode
	 *            from data
	 */
	private void setZip(int zip) {
		String s = String.format("%05d", zip); // makes sure it adds preceding
												// zeros, e.g.: input: 50,
												// output: 00050

		zip = Integer.parseInt(s);
		this.zipcode = zip;
	}

	/**
	 * Gets a Tree object's zipcode
	 * 
	 * @return zipcode of tree
	 */
	public int getZip() {

		return this.zipcode;
	}

	/**
	 * Sets a Tree object's borough
	 * 
	 * @param borough
	 *            from data file
	 */
	private void setBoro(String boro) {
		this.boroname = boro;
	}

	/**
	 * Gets a Tree object's bourough name
	 * 
	 * @return borough name of tree
	 */
	public String getBoro() {
		return this.boroname;
	}

	/**
	 * Sets a Tree object's x
	 * 
	 * @param x_sp
	 *            from data
	 */
	private void setX(double x) {
		this.x_sp = x;
	}

	/**
	 * Gets a Tree object's x
	 * 
	 * @return x
	 */
	public double getX() {
		return this.x_sp;
	}

	/**
	 * Sets a Tree object's y
	 * 
	 * @param y_sp
	 *            from data
	 */
	private void setY(double y) {
		this.y_sp = y;
	}

	/**
	 * Gets a Tree object's y
	 * 
	 * @return y
	 */
	public double getY() {
		return this.y_sp;
	}

}
