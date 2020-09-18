package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class is a POJO representation of a GameInformation class with added JPA
 * annotations.
 * 
 * @author Luis Cabral
 */
@Entity
@Table(name = "video_games")
public class GameInformation {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private int id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "RELEASE_YEAR")
	private int year;
	@Column(name = "PLATFORMS")
	private String platforms;
	@Column(name = "GENRE")
	private String genre;

	public GameInformation() {

	}

	/**
	 * Constructor that takes in all arguments but the id.
	 * @param name - Name of a video game
	 * @param year - Year the game was released
	 * @param platforms - Platforms game was released on
	 * @param genres - The games genres
	 */
	public GameInformation(String name, int year, String platforms, String genres) {
		this.name = name;
		this.year = year;
		this.platforms = platforms;
		this.genre = genres;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * @return the platforms
	 */
	public String getPlatforms() {
		return platforms;
	}

	/**
	 * @param platforms the platforms to set
	 */
	public void setPlatforms(String platforms) {
		this.platforms = platforms;
	}

	/**
	 * @return the genres
	 */
	public String getGenre() {
		return genre;
	}

	/**
	 * @param genres the genres to set
	 */
	public void setGenre(String genres) {
		this.genre = genres;
	}

	/**
	 * Returns a string holding the game info
	 * @return String holding game information.
	 */
	public String printGames() {
		return " || " + id + " || " + name + " || " + year + " || " + platforms + " || " + genre + " || ";
	}
}
