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
    app.test();
//		app.launch();
	}

	private void test() {
		Film film = db.findFilmById(1);
		Actor actor = db.findActorById(2);
//		List<Actor> actorList = db.findActorsByFilmId(45);
//		System.out.println(film);
//		System.out.println(actor);
//		System.out.println(actorList);
//		System.out.println(film.getActorList());
		for(Actor a : film.getActorList()) {
			System.out.println(a);
		}
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		
		
		System.out.println("Please select an option from the following menu");
		System.out.println("1. Look up a film by it's ID");
		System.out.println("2. Look up a film by a search keyword");
		System.out.println("3. Exit the application");
		
		boolean keepGoing = true;
		String userInput = input.next();
		while(keepGoing) {
			if(userInput == "1") {
				db.findActorById(input.nextInt());
			}
	
		}

		
		
	}

}
