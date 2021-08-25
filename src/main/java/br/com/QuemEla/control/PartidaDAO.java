package br.com.QuemEla.control;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.QuemEla.model.Partida;

public class PartidaDAO {
	@Autowired
	static EntityManager manager = EntityFactory.getManager();
	
	public void salvarPartida(Partida partida) {
		try {
			manager.getTransaction().begin();
			manager.persist(partida);
			manager.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}
