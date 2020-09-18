package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.GameInformation;

/**
 * This helper Class is used to take in data from the GameDataBaseView class and
 * to persist that data to the database.
 * 
 * @author Luis Cabral
 *
 */
public class GameInformationHelper {

	// Global entity manager
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("ConsoleGameDatabase");

	/**
	 * Adds an entire game row to the database.
	 * 
	 * @param game - A GameInformation object.
	 */
	public void insertGame(GameInformation game) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(game);
		em.getTransaction().commit();
	}

	/**
	 * Returns a list holding all the records in the database.
	 * 
	 * @return List of GameInformtion objects.
	 */
	public List<GameInformation> showAllGames() {
		EntityManager em = emfactory.createEntityManager();
		List<GameInformation> allGames = em.createQuery("SELECT i FROM GameInformation i").getResultList();
		return allGames;
	}

	/**
	 * Deletes a record based on a title input.
	 * 
	 * @param game - GameInformation object to be deleted.
	 */
	public void deleteByTitle(GameInformation game) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<GameInformation> typedQuery = em
				.createQuery("SELECT li FROM GameInformation li WHERE li.name =:selectedName", GameInformation.class);

		typedQuery.setParameter("selectedName", game.getName());
		typedQuery.setMaxResults(1);

		GameInformation result = typedQuery.getSingleResult();

		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * Deletes a GameInformation object based on an id value.
	 * 
	 * @param game - GameInformation object to be deleted.
	 */
	public void deleteByID(GameInformation game) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<GameInformation> typedQuery = em
				.createQuery("SELECT li FROM GameInformation li WHERE li.id =:selectedID", GameInformation.class);

		typedQuery.setParameter("selectedID", game.getId());
		typedQuery.setMaxResults(1);

		GameInformation result = typedQuery.getSingleResult();

		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * Searches for a record in the database by name.
	 * 
	 * @param name - String holding the name to search by.
	 * @return a List holding all the GameInformation records that matched the
	 *         search.
	 */
	public List<GameInformation> searchByName(String name) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();

		TypedQuery<GameInformation> typedQuery = em
				.createQuery("SELECT li FROM GameInformation li WHERE li.name = :selectedName", GameInformation.class);
		typedQuery.setParameter("selectedName", name);

		List<GameInformation> gamesFound = typedQuery.getResultList();
		em.close();
		return gamesFound;
	}

	/**
	 * Searches for a record in the database by id.
	 * 
	 * @param idToEdit - int id value of the GameInformation object.
	 * @return The record with the id value.
	 */
	public GameInformation searchForItemById(int idToEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		GameInformation found = em.find(GameInformation.class, idToEdit);
		em.close();
		return found;
	}

	/**
	 * Updates a game record with a new set of values.
	 * 
	 * @param gameUpdate - GameInformation object holding the updated record.
	 */
	public void updateGame(GameInformation gameUpdate) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(gameUpdate);
		em.getTransaction().commit();
		em.close();

	}
}
