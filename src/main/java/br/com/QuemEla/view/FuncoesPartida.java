package br.com.QuemEla.view;

import org.springframework.web.bind.annotation.GetMapping;


import com.google.gson.Gson;

import br.com.QuemEla.control.PartidaDAO;
import br.com.QuemEla.control.PerguntaDAO;
import br.com.QuemEla.control.PersonagemDAO;
import br.com.QuemEla.model.Rodada;

public class FuncoesPartida {
	Gson gson = new Gson();
	
	PartidaDAO pdao = new PartidaDAO();
	PersonagemDAO perdao = new PersonagemDAO();
	PerguntaDAO pergdao = new PerguntaDAO();
	
	@GetMapping(path = "/partida/nova", produces= { "application/json"})
	public String getRodadas() {
		Rodada rodada = new Rodada();
		rodada.setFigura_misteriosa(perdao.getPersonagemAleatorio());
		rodada.setPergunta(pergdao.getPerguntaAleatoria());
		return gson.toJson("");
	}
}
