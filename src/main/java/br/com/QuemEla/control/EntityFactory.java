package br.com.QuemEla.control;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityFactory {
	static EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistence.xml");
	
	static EntityManager manager = factory.createEntityManager();
	
	public static EntityManager getManager() {
		return manager;
	}
}
