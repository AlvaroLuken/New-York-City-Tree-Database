
/* 
 * TreeList.java
 * Data Structures CS-102, New York University
 * 
 * @author Alvaro Luken 
 * (aluken@nyu.edu)
 * 
 * This class is used to store all Tree objects.
 * It serves as an ArrayList object of only Tree objects.
 * 
 * The class contains various methods for acquiring data from a Tree object
 * and is able to print the manipulated data in a neat way.
 * 
 */

import java.util.ArrayList;

public class TreeCollection extends ArrayList<Tree> {

	// ArrayList<Tree> treeList = new ArrayList<Tree>(); //creates an empty list
	// to store Tree objects
	ArrayList<String> treeListMatches = new ArrayList<>(); // list I use for
															// matches in
															// various methods
	ArrayList<String> boroMatches = new ArrayList<>();
	ArrayList<String> bothMatch = new ArrayList<>();

	/**
	 * Constructor: Creates a new TreeList object but takes no arguments The
	 * constructor is empty and automatically calls the superclass constructor
	 * to construct the object (which is the ArrayList<Tree> class this class
	 * extends)
	 * 
	 */
	public TreeCollection() {

		// empty
		// automatically calls the superclass constructor

	}

	/**
	 * Gets the total number of Tree objects in the TreeList object
	 * 
	 * @return the size of the TreeList arraylist
	 */
	public int getTotalNumberOfTrees() { // returns total number of Tree objects
		return this.size();

	}

	/**
	 * Gets the number of Tree objects in TreeList whose species matches the
	 * specified
	 * 
	 * @param speciesName
	 * @return size of arraylist populated by matches
	 */
	public int getCountByTreeSpecies(String speciesName) { // returns number of
															// Tree objects
															// whose species
															// mathces the
															// specified
															// speciesName
		treeListMatches.clear();

		for (int i = 0; i < this.size() - 1; i++) {
			if (this.get(i).getName().equalsIgnoreCase(speciesName)) {
				// System.out.println("HERE!");
				treeListMatches.add(speciesName);
			}
		}
		return treeListMatches.size();
	}

	/**
	 * Gets the number of Tree objects in TreeList whose borough matches the
	 * specified
	 * 
	 * @param String
	 *            boroName
	 * @return size of arraylist populated by matches of boroName
	 */
	public int getCountByBorough(String boroName) { // returns the number of
													// Tree objects that are
													// located in the specified
													// borough
		boroMatches.clear();

		for (int i = 0; i <= this.size() - 1; i++) {
			if (this.get(i).getBoro().equalsIgnoreCase(boroName)) {
				// count++;
				boroMatches.add(boroName);
			}
		}

		return boroMatches.size();
	}

	/**
	 * Gets the number of Tree objects in TreeList whose borough AND species
	 * name matches the specified
	 * 
	 * @param String
	 *            boroName, String speciesName
	 * @return size of arraylist populated by matches of boroName and
	 *         speciesName
	 */
	public int getCountByTreeSpeciesBorough(String speciesName, String boroName) {
		int count = 0;
		for (int i = 0; i <= this.size() - 1; i++) {
			if (this.get(i).getBoro().contains(boroName) && this.get(i).getName().equalsIgnoreCase(speciesName)) {

				count++;
			}
		}

		return count;
	}

	/**
	 * Gets the number of Tree objects in TreeList whose species name matches
	 * the specified
	 * 
	 * @param String
	 *            speciesName
	 * @return arraylist populated (of Tree objects) by matches of speciesName
	 *         without duplicates
	 */
	public ArrayList<String> getMatchingSpecies(String speciesName) {

		ArrayList<String> species = new ArrayList<String>();

		String adder = "";
		for (int i = 0; i <= this.size() - 1; i++) {

			if (this.get(i).getName().toLowerCase().contains(speciesName.toLowerCase())) {
				boolean isFound = false;

				adder = this.get(i).getName();
				for (int j = 0; j < species.size(); j++) {
					if (this.get(i).getName().equalsIgnoreCase(species.get(j))) {
						isFound = true;

					}
				}
				if (!(isFound)) {
					species.add(adder);
				}

			}

		}
		return species;
	}

	int total = 0;
	int boroSpeciesTotalManhattan = 0;
	int boroSpeciesTotalBrooklyn = 0;
	int boroSpeciesTotalSI = 0;
	int boroSpeciesTotalBronx = 0;
	int boroSpeciesTotalQueens = 0;
	int boro = 0;

	/**
	 * Prints the popularity of Tree objects in NYC in a neat and formatted way
	 * 
	 * @param ArrayList
	 *            results - an arraylist of the results that match the user's
	 *            specified Tree species name
	 * 
	 */
	public void popularity(ArrayList results) {
		total = 0;
		boroSpeciesTotalManhattan = 0;
		boroSpeciesTotalBrooklyn = 0;
		boroSpeciesTotalBronx = 0;
		boroSpeciesTotalSI = 0;
		boroSpeciesTotalQueens = 0;

		for (int i = 0; i <= results.size() - 1; i++) {
			total += this.getCountByTreeSpecies((String) results.get(i));
			// total += this.getCountByTreeSpecies(this.get(i).getName());
			// System.out.println(" Tijuana " + this.get(i).getBoro());

			boroSpeciesTotalManhattan += this.getCountByTreeSpeciesBorough((String) results.get(i), "Manhattan");
			boroSpeciesTotalBrooklyn += this.getCountByTreeSpeciesBorough((String) results.get(i), "Brooklyn");
			boroSpeciesTotalBronx += this.getCountByTreeSpeciesBorough((String) results.get(i), "Bronx");
			boroSpeciesTotalSI += this.getCountByTreeSpeciesBorough((String) results.get(i), "Staten Island");
			boroSpeciesTotalQueens += this.getCountByTreeSpeciesBorough((String) results.get(i), "Queens");

		} // bo : total ( Total ) # %
		System.out.printf("%1$-18s %2$5s %3$,8d %4$1s %5$,1d %6$1s %7$12.2f %8$1s %n", "   NYC", ":", total, "(",
				this.getTotalNumberOfTrees(), ")", (((double) total / (double) (this.getTotalNumberOfTrees())) * 100),
				"%");
		System.out.printf("%1$-20s %2$3s %3$,8d %4$1s %5$,1d %6$1s %7$15.2f %8$1s %n", "   Manhattan", ":",
				boroSpeciesTotalManhattan, "(", this.getCountByBorough("Manhattan"), ")",
				(((double) boroSpeciesTotalManhattan) / (double) (this.getCountByBorough("Manhattan"))) * 100, "%");
		System.out.printf("%1$-18s %2$5s %3$,8d %4$1s %5$,1d %6$1s %7$14.2f %8$1s %n", "   Bronx", ":",
				boroSpeciesTotalBronx, "(", this.getCountByBorough("Bronx"), ")",
				(((double) boroSpeciesTotalBronx) / (double) (this.getCountByBorough("Bronx"))) * 100, "%");
		System.out.printf("%1$-19s %2$4s %3$,8d %4$1s %5$,1d %6$1s %7$14.2f %8$1s %n", "   Brooklyn", ":",
				boroSpeciesTotalBrooklyn, "(", this.getCountByBorough("Brooklyn"), ")",
				(((double) boroSpeciesTotalBrooklyn) / (double) (this.getCountByBorough("Brooklyn"))) * 100, "%");
		System.out.printf("%1$-18s %2$5s %3$,8d %4$1s %5$,1d %6$1s %7$15.2f %8$1s %n", "   Queens", ":",
				boroSpeciesTotalQueens, "(", this.getCountByBorough("Queens"), ")",
				(((double) boroSpeciesTotalQueens) / (double) (this.getCountByBorough("Queens"))) * 100, "%");
		System.out.printf("%1$-18s %2$5s %3$,8d %4$1s %5$,1d %6$1s %7$14.2f %8$1s %n", "   Staten Island", ":",
				boroSpeciesTotalSI, "(", this.getCountByBorough("Staten Island"), ")",
				(((double) boroSpeciesTotalSI) / (double) (this.getCountByBorough("Staten Island"))) * 100, "%");

		System.out.println();

	}

}
