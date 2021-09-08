package br.com.QuemEla.control;

import java.util.ArrayList;

import java.util.Random;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.QuemEla.model.Categoria;
import br.com.QuemEla.model.Pergunta;
import br.com.QuemEla.model.PerguntaPersonagem;
import br.com.QuemEla.model.Personagem;

public class PerguntaDAO {
	@Autowired
	static EntityManager manager = EntityFactory.getManager();
	
	//Sobre pergunta
	
	public void salvarPergunta(Pergunta pergunta) {
		try {
			manager.getTransaction().begin();
			manager.persist(pergunta);
			manager.getTransaction().commit();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Pergunta> listarPerguntas() {
		ArrayList<Pergunta> lista = new ArrayList<Pergunta>();
		try {
			lista = (ArrayList<Pergunta>) manager.createQuery("SELECT p FROM Pergunta p").getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public String getRespostaPergunta(Pergunta pergunta, Personagem personagem) {

		String resposta = "";
		
		try {
		
			PerguntaPersonagem r = (PerguntaPersonagem) manager.createQuery("SELECT r FROM PerguntaPersonagem r WHERE r.pergunta = :pergunta AND r.personagem =: personagem")
					.setParameter("pergunta", pergunta).setParameter("personagem", personagem).getSingleResult();
			
			resposta = r.getResposta();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return resposta;
	}
	
	public Pergunta getPerguntaById(int id) {
		Pergunta pergunta = manager.find(Pergunta.class, id);
		return pergunta;
	}
	
	public Pergunta getPerguntaAleatoria() {
		ArrayList<Pergunta> lista = listarPerguntas();
		Random rand = new Random();
		int posicaoAleatoria = rand.nextInt(lista.size());
		return lista.get(posicaoAleatoria);
	}
	
	public ArrayList<Pergunta> listarPerguntasByCategoria(int idCategoria){
		ArrayList<Pergunta> lista = new ArrayList<Pergunta>();
		
		Categoria categoria = getCategoriaById(idCategoria);
		
		try {
			lista = (ArrayList<Pergunta>) manager.createQuery("SELECT p FROM Pergunta p WHERE p.categoria = :categoria")
					.setParameter("categoria", categoria).getResultList();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	//Sobre categoria
	
	public void salvarCategoria(Categoria categoria) {
		manager.getTransaction().begin();
		manager.persist(categoria);
		manager.getTransaction().commit();
	}
	
	public ArrayList<Categoria> listarCategorias() {
		ArrayList<Categoria> lista = new ArrayList<Categoria>();
		try {
			lista = (ArrayList<Categoria>) manager.createQuery("SELECT c FROM Categoria c").getResultList();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public Categoria getCategoriaById(int id) {
		Categoria categoria = null;
		categoria = manager.find(Categoria.class, id);
		return categoria;
	}
}
