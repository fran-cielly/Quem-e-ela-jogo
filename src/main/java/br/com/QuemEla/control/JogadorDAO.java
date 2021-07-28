package br.com.QuemEla.control;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.QuemEla.model.Jogador;

public class JogadorDAO {
	@Autowired
	static EntityManager manager = EntityFactory.getManager();

	public void salvarJogador(Jogador jogador) {
		manager.getTransaction().begin();
		manager.persist(jogador);
		manager.getTransaction().commit();
	}
	
	//retorna true se o nome não estiver cadastrado -  retorna false se o nome ja estiver cadastrado
	public boolean verificaNome(String nome) {
		Jogador jogador = null;
		try {
			jogador = (Jogador) manager.createQuery("SELECT j FROM Jogador j where j.nome= :nome ").setParameter("nome", nome).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		if(jogador == null) {
			return true;
		}else {
			return false;
		}
	}

	public Jogador getJogadorByEmail(String nome, String senha) {
		Jogador jogador = null;
		// jogador = manager.find(Jogador.class, 1);
		try {
			jogador = (Jogador) manager.createQuery("SELECT j FROM Jogador j where j.nome= :nome and j.senha= :senha")
					.setParameter("nome", nome).setParameter("senha", senha).getSingleResult();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return jogador;
	}
	
	public Jogador getJogadorById(int id) {
		Jogador jogador = null;
		manager.find(Jogador.class, id);
		return jogador;
	}
}
