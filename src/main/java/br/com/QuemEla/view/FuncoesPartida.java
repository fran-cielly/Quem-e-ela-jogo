package br.com.QuemEla.view;

import java.util.GregorianCalendar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.QuemEla.control.PartidaDAO;
import br.com.QuemEla.control.PerguntaDAO;
import br.com.QuemEla.control.PersonagemDAO;
import br.com.QuemEla.model.Partida;
import br.com.QuemEla.model.Rodada;
import br.com.QuemEla.sessao.Sessao;

@RestController
public class FuncoesPartida {
	Gson gson = new Gson();
	private GregorianCalendar date = new GregorianCalendar();
	
	PartidaDAO pdao = new PartidaDAO();
	PersonagemDAO perdao = new PersonagemDAO();
	PerguntaDAO pergdao = new PerguntaDAO();
	
	@GetMapping(path = "/partida/nova", produces= { "application/json"})
	public String getPartida() {
		Partida partidaAtual = Sessao.getPartida();
		
		//Se não existir uma partida em andamento uma será criada
		if(partidaAtual == null) {
			Partida partida = new Partida();
			
			for (int i = 0; i < 3; i++) {
				Rodada rodada = new Rodada();
				rodada.setFigura_misteriosa(perdao.getPersonagemAleatorio());
				
				partida.getRodadas().add(rodada);
			}
			partida.setData_hora_jogo(date.getTime());
			partida.setJogador1(Sessao.getJogadorLogado());
			
			//pdao.salvarPartida(partida);
			Sessao.setPartida(partida);
			return gson.toJson(partida);
			
		//Se houver uma partida em andamento ela será retornada
		}else {
			return gson.toJson(partidaAtual);
		}
	}
}
