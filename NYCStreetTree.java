
/* 
 * NYCTrees.java
 * Data Structures CS-102, New York University
 * 
 * @author Alvaro Luken 
 * (aluken@nyu.edu)
 * 
 * This is a program is designed to provide a tool for visualizing
 * popularity of New York City strees trees in different boroughs
 * of the city. (Using input file data) (input is from user and on console)
 * 
 * This class is part of the package: NYCStreetTrees, Tree, TreeList
 * 
 * This class contains the main method for the package and is responsible for opening
 * and reading the specified data files.
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class NYCStreetTree {

	public static void main(String[] args) throws FileNotFoundException {
		Scanner input = new Scanner(System.in);

		System.out.println("Enter file name.");

		String file_name = args[0]; // soft code so that it works from terminal
		File file = new File(file_name);

		if (file_name.length() == 0) { // checks if user inputs blank
			System.err.println("Usage Error: the program expects file name as an argument");
			System.exit(-1); // these calls terminate the program
		}

		
		if (!(file.canRead())) { // checks if file can be read
			System.err.println("ERROR: The file " + args[0] + "cannot be opened.");
			System.exit(-1);
		}
		if (!(file.isFile())) { // checks if file is actually a file
			System.err.println("ERROR: The program expects a file.");
			System.exit(-1);
		}

		// if(!(file.isHidden())) { //checks if file is visible or hidden
		// System.err.println("ERROR: File must be visible");
		// System.exit(-1);
		// }

		Scanner input_file = new Scanner(file);

		ArrayList<String> list = new ArrayList<>(); // populates ArrayList with
													// file elements
		ArrayList<String> arr = new ArrayList<>();

		TreeCollection z = new TreeCollection();

		input_file.nextLine();
		while (input_file.hasNextLine()) {

			list = splitCSVLine(input_file.nextLine());

			// id //diam //status //health //name
			Tree a = new Tree(Integer.parseInt(list.get(0)), Integer.parseInt(list.get(3)), list.get(6), list.get(7),
					list.get(9),
					// zip //boro //x //y
					Integer.parseInt(list.get(25)), list.get(29), Double.parseDouble(list.get(39)),
					Double.parseDouble(list.get(40)));

			z.add(a);
			arr.add(list.get(9));

		}

		input_file.close(); // close file

		System.out.println("Enter the tree species to learn more about it or enter \"quit\" to stop");

		ArrayList<String> results = new ArrayList<>(); // for results from
														// getMatchingSpecies

		while (true) {
			String choice = input.nextLine();
			if (choice.equalsIgnoreCase("quit")) {
				System.out.println("Thank you for using the program.");
				System.exit(-1);
			} else {
				if (z.getMatchingSpecies(choice).size() >= 1) {
					System.out.println("All matching species: ");
					results = z.getMatchingSpecies(choice);

					printResults(results);
					// popularity
					System.out.println();
					System.out.println("Popularity in the city");
					z.popularity(results);
					System.out.println("Enter the tree species to learn more about it or enter \"quit\" to stop");

				} else {
					System.err.println("There are no records of " + choice + " on NYC streets");
					System.out.println();
					System.out.println("Enter the tree species to learn more about it or enter \"quit\" to stop");
				}
			}
		}
	}

	public static void printResults(ArrayList results) {
		for (int i = 0; i <= results.size() - 1; i++) {
			System.out.println("   " + results.get(i) + "\t");
		}
	}

	/**
	 * Splits the given line of a CSV file according to commas and double quotes
	 * (double quotes are used to surround multi-word entries that may contain
	 * commas).
	 * 
	 * @param textLine
	 *            line of text to be parsed
	 * @return an ArrayList object containing all individual entries/tokens
	 *         found on the line
	 */

	public static ArrayList<String> splitCSVLine(String textLine) {
		ArrayList<String> entries = new ArrayList<String>();
		int lineLength = textLine.length();
		StringBuffer nextWord = new StringBuffer();
		char nextChar;
		boolean insideQuotes = false;
		boolean insideEntry = false;

		// iterate over all character in the textLine
		for (int i = 0; i < lineLength; i++) {
			nextChar = textLine.charAt(i);

			// handle smart quotes as well as regular quotes

			if (nextChar == '"' || nextChar == '\u201C' || nextChar == '\u201D') {
				// change insideQuotes flag when nextChar is a quote
				if (insideQuotes) {
					insideQuotes = false;
					insideEntry = false;
				} else {
					insideQuotes = true;
					insideEntry = true;
				}
			} else if (Character.isWhitespace(nextChar)) {
				if (insideQuotes || insideEntry) {
					// add it to the currency entry
					nextWord.append(nextChar);
				} else { // skip all spaces between entries
					continue;
				}
			} else if (nextChar == ',') {
				if (insideQuotes) { // comma inside an entry
					nextWord.append(nextChar);
				} else { // end of entry found
					insideEntry = false;
					entries.add(nextWord.toString());
					nextWord = new StringBuffer();
				}
			} else {
				// add all other characters to the nextWord
				nextWord.append(nextChar);
				insideEntry = true;
			}
		}
		// add the last word (assuming not empty)
		// trim the white space before adding to the list
		if (!nextWord.toString().equals("")) {
			entries.add(nextWord.toString().trim());
		}

		return entries;
	}

}
