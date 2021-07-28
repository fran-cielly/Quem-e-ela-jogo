package br.com.QuemEla.control;

import java.util.ArrayList;

import java.util.Random;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.QuemEla.model.Personagem;

public class PersonagemDAO {
	@Autowired
	static EntityManager manager = EntityFactory.getManager();
	
	public ArrayList<Personagem> listarPersonagens() {
		ArrayList<Personagem> lista = new ArrayList<Personagem>(); 
		try {
			lista = (ArrayList<Personagem>) manager.createQuery("SELECT p FROM Personagem p").getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public Personagem getPersonagemAleatorio() {
		ArrayList<Personagem> lista = listarPersonagens();
		Random rand = new Random();
		int posicaoAleatoria = rand.nextInt(lista.size());
		return lista.get(posicaoAleatoria);
	}
	
	public Personagem getPersonagemById(int id) {
		Personagem p = manager.find(Personagem.class, id);
		return p;
	}
}
