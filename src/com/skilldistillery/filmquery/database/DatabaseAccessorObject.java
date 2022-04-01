package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid?useSSL=false&useLegacyDatetimeCode=false&serverTimezone=US/Mountain";
	private String user = "student";
	private String pass = "student";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.err.println("Driver not found.");
			throw new RuntimeException("Unable to load MYSQL driver class");
		}

	}

	@Override
	public Film findFilmById(int filmId) {
		Film fm = null;
		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			String sqltxt;
			sqltxt = "SELECT * FROM film WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sqltxt);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				fm = new Film();
				fm.setId(rs.getInt("id"));
				fm.setTitle(rs.getString("title"));
				fm.setDescription(rs.getString("description"));
				fm.setReleaseYear(rs.getInt("release_year"));
				fm.setLanguageId(rs.getInt("language_id"));
				fm.setRentalRate(rs.getDouble("rental_rate"));
				fm.setLength(rs.getInt("length"));
				fm.setReplacementCost(rs.getDouble("replacement_cost"));
				fm.setRating(rs.getString("rating"));
				fm.setSpecialFeatures(rs.getString("special_features"));

			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Invalid Response");
		}
		return fm;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			String sqltxt;
			sqltxt = "SELECT * FROM actor WHERE id = ?";
			PreparedStatement stmt = conn.prepareStatement(sqltxt);
			stmt.setInt(1, actorId);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Invalid Response");
		}
		return actor;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> actorList = new ArrayList<>();
		Connection conn;
		try {
			conn = DriverManager.getConnection(URL, user, pass);
			String sqltxt;
			sqltxt = "SELECT a.id, a.first_name, a.last_name FROM Actor a JOIN film_actor fa ON a.id = fa.actor_id JOIN film f ON f.id = fa.film_id WHERE fa.film_id = ?";
			PreparedStatement stmt = conn.prepareStatement(sqltxt);
			stmt.setInt(1, filmId);
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) {
				Actor actor = new Actor();
				actor.setId(rs.getInt("id"));
				actor.setFirstName(rs.getString("first_name"));
				actor.setLastName(rs.getString("last_name"));
				actorList.add(actor);
			}
			rs.close();
			stmt.close();
			conn.close();

		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("Invalid Response");
		}
		return actorList;
	}

}
