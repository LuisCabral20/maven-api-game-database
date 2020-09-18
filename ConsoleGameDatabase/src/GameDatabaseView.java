import java.util.List;
import java.util.Scanner;

import controller.GameInformationHelper;
import model.GameInformation;

/**************************************************************
 * Name : Week 3 Assessment - JPA Author : Luis Cabral Created : 09/17/2020
 * Course : CIS 175 Java 2 Version : 1.0 OS : Windows 10 Copyright : This is my
 * own original work based on specifications issued by our instructor
 * Description : This class is used gather information from the user through the
 * console. This allows a user to update, delete, view, and add new data to a
 * database using the Java persistence API. This project utilizes a database
 * holding information about video games. Input: name - Game title release year
 * - Game release year platforms - Platforms game was released on genres - Games
 * genres Output: Academic Honesty: I attest that this is my original work. I
 * have not used unauthorized source code, either modified or unmodified. I have
 * not given other fellow student(s) access to my program.
 ***************************************************************/
public class GameDatabaseView {

	// Global Scanner & GameInformationHelper object
	static Scanner sc = new Scanner(System.in);
	static GameInformationHelper helper = new GameInformationHelper();

	/**
	 * Gets the user to enter information about
	 * a game record for the database.
	 */
	private static void addGame() {

		// Get name
		System.out.print("     Enter game name: ");
		String name = sc.nextLine();

		try {

			System.out.print("     Enter release year: ");
			int year = Integer.parseInt(sc.nextLine());

			// Get Platforms
			System.out.print("     Enter platforms: ");
			String platforms = sc.nextLine();

			// Get Genre
			System.out.print("     Enter genres: ");
			String genre = sc.nextLine();

			// Create game object
			GameInformation newGame = new GameInformation(name, year, platforms, genre);

			// Add item to Database
			helper.insertGame(newGame);

		} catch (Exception e) {
			System.out.println(e.toString() + " Invalid year input\n");
		}
	}

	/**
	 * Gets the user to enter information about
	 * a game record to delete.
	 */
	private static void deleteGame() {

		int selection;

		// How do they want to delete the game
		System.out.println("     Select search method:");
		System.out.println("     1.) By Title.");
		System.out.println("     2.) By ID.");
		System.out.print("     Search: ");

		try {
			selection = Integer.parseInt(sc.nextLine());

			if (selection == 1) {
				// Search by title
				System.out.println("     Enter game title");
				System.out.print("     Title: ");
				String title = sc.nextLine();
				
				GameInformation game = new GameInformation();
				game.setName(title);
				helper.deleteByTitle(game);

			} else if (selection == 2) {
				// Search by ID
				System.out.println("     Enter an ID");
				System.out.print("     ID: ");
				
				try {
					int gameID = Integer.parseInt(sc.nextLine());
					GameInformation game = new GameInformation();
					
					game.setId(gameID);
					helper.deleteByID(game);
					
				} catch (Exception ex) {
					System.out.println(ex.toString() + "  Invalid ID");
					
				}
			} else {
				System.out.println("     Input must be 1 or 2.\n");

			}
		} catch (Exception e) {
			System.out.println(e.toString() + " Invalid type input\n");

		}
	}

	/**
	 * Gets the user to enter information about
	 * a game record they wish to update.
	 */
	private static void updateSection() {
		List<GameInformation> gamesFound;
		System.out.println("Enter the games name: ");
		String name = sc.nextLine();
		gamesFound = helper.searchByName(name);

		// Check if it found anythings
		if (!gamesFound.isEmpty()) {
			// Print results
			System.out.println("Select ID");
			for (GameInformation game : gamesFound) {
				System.out.println(game.getId() + " : " + game.toString()); 
			}

			// Get id user wants to edit
			int id = Integer.parseInt(sc.nextLine());

			GameInformation toEdit = helper.searchForItemById(id);

			// Select section to update
			System.out.println("Pick section to update");
			System.out.println("1.) Title.");
			System.out.println("2.) Year.");
			System.out.println("3.) Platforms.");
			System.out.println("4.) Genre");

			int updateSelection = Integer.parseInt(sc.nextLine());

			if (updateSelection == 1) {
				System.out.println("Enter new title");
				String newTitle = sc.nextLine();
				toEdit.setName(newTitle);

			} else if (updateSelection == 2) {
				System.out.println("Enter new Year");
				int newYear = Integer.parseInt(sc.nextLine());
				toEdit.setYear(newYear);

			} else if (updateSelection == 3) {
				System.out.println("Enter new Platforms");
				String newPlatforms = sc.nextLine();
				toEdit.setPlatforms(newPlatforms);

			} else if (updateSelection == 4) {
				System.out.println("Enter new Genres");
				String newGenres = sc.nextLine();
				toEdit.setGenre(newGenres);

			} else {
				System.out.println("Invalid section selection.");
			}

			helper.updateGame(toEdit);

		} else {
			System.out.println("No titles found.");
		}

	}

	/**
	 * Prints all the the records held in the database
	 */
	private static void viewAll() {
		List<GameInformation> allGames = helper.showAllGames();
		for (GameInformation game : allGames) {
			System.out.println(game.printGames());
		}
	}
	
	public static void main(String[] args) {
		runView();
	}

	/**
	 * Runs the console output menu
	 */
	public static void runView() {

		boolean runLoop = true;
		// loop until user exits
		do {
			System.out.println("\n===== Game List =====");
			System.out.println("     Select an option:");
			System.out.println("     1.) View Database.");
			System.out.println("     2.) Add a game.");
			System.out.println("     3.) Update row.");
			System.out.println("     4.) Delete game by title or ID.");
			System.out.println("     5.) Exit. ");
			System.out.print("     Selected option: ");

			int selection = sc.nextInt();
			sc.nextLine();

			System.out.print("=====================\n");

			switch (selection) {
			case 1:
				viewAll();
				break;
			case 2:
				addGame();
				break;
			case 3:
				updateSection();
				break;
			case 4:
				deleteGame();
				break;
			default:
				System.out.println("     Program Ended.");
				runLoop = false;
				// clean up
				break;
			}

		} while (runLoop == true);
		System.out.println("=======================");
	}
}
