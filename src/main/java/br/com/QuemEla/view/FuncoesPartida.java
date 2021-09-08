package br.com.QuemEla.view;

import java.util.GregorianCalendar;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.QuemEla.control.ListaDeMensagens;
import br.com.QuemEla.control.PartidaDAO;
import br.com.QuemEla.control.PerguntaDAO;
import br.com.QuemEla.control.PersonagemDAO;
import br.com.QuemEla.model.Entrada;
import br.com.QuemEla.model.Partida;
import br.com.QuemEla.model.Pergunta;
import br.com.QuemEla.model.Personagem;
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
	
	@PostMapping(path = "/partida/perguntar", produces= { "application/json"})
	public String fazerPergunta(@RequestBody Entrada entrada) {
		
		Rodada rodadaAtual = Sessao.getRodadaAtual();
		
		if(rodadaAtual.getPergunta() == null){
		
			Pergunta pergunta = pergdao.getPerguntaById(entrada.getId());

			Personagem personagem = rodadaAtual.getFigura_misteriosa();
			String resposta = pergdao.getRespostaPergunta(pergunta, personagem);
			
			rodadaAtual.setPergunta(pergunta);
				
			return gson.toJson(resposta);
		}else {
			return gson.toJson(ListaDeMensagens.getMensagem("rodada acabou"));
		}
	}
	
	@PostMapping(path = "/partida/tentativa", produces= { "application/json"})
	public String tentativa(@RequestBody Entrada entrada) {
		
		Personagem personagemRodada = Sessao.getRodadaAtual().getFigura_misteriosa();
		
		if(personagemRodada.getId() == entrada.getId()) {
			return gson.toJson(ListaDeMensagens.getMensagem("acertou personagem"));
			
		}else {
			return gson.toJson(ListaDeMensagens.getMensagem("errou personagem"));
		}
	}
}
