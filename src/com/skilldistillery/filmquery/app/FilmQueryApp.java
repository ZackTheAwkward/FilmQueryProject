package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
//    app.test();
		app.launch();
	}

	private void test() {
		Film film = db.findFilmById(1);
		Actor actor = db.findActorById(2);
		List<Actor> actorList = db.findActorsByFilmId(45);
		System.out.println(film);
		System.out.println(actor);
		System.out.println(actorList);
		System.out.println(film.getActorList());
		for (Actor a : film.getActorList()) {
			System.out.println(a);
		}
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {

		boolean keepGoing = true;
		while (keepGoing) {
			System.out.println("Please select an option from the following menu");
			System.out.println("1. Look up a film by it's ID");
			System.out.println("2. Look up a film by a search keyword");
			System.out.println("3. Exit the application");

			int userInput = input.nextInt();
			input.nextLine();

			if (userInput == 1) {
				System.out.println("Enter the number of the film ID you wish to look up");
				int userId = input.nextInt();
				Film film = db.findFilmById(userId);
				if (film == null) {
					System.out.println("Film not found, please try again");
				} else {
					System.out.println("Title: " + film.getTitle() + ", Release Year: " + film.getReleaseYear()
							+ ", Rating: " + film.getRating() + ", Descripton: " + film.getDescription());
				}
			}
			if (userInput == 2) {
				System.out.println("Please enter the keyword you would like to search the film by");
				String search = input.next();
				Film fm = db.findFilmByKeyword(search);
				if (fm == null) {
					System.out.println("Film not found, please try again");
				} else {
					continue;
				}

			}
			if (userInput == 3) {
				System.out.println("GoodBye!");
				keepGoing = false;
			}

		}

	}

}
