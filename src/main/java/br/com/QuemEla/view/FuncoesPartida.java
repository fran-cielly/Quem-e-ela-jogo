package br.com.QuemEla.view;

import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;

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
import br.com.QuemEla.model.Jogador;
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
	public String getPartida(HttpServletRequest request) {
		Partida partidaAtual = Sessao.getPartida(request);
		//Partida partidaAtual = (Partida) request.getSession().getAttribute("partidaAtual");
		
		//Se não existir uma partida em andamento uma será criada
		if(partidaAtual == null) {
			Partida partida = new Partida();
			
			for (int i = 0; i < 3; i++) {
				Rodada rodada = new Rodada();
				rodada.setFigura_misteriosa(perdao.getPersonagemAleatorio());
				
				partida.getRodadas().add(rodada);
			}
			partida.setData_hora_jogo(date.getTime());
			partida.setPontuacao_jogador1(0);
			partida.setJogador1(Sessao.getJogadorLogado(request));
			//partida.setJogador1((Jogador) request.getSession().getAttribute("jogadorLogado"));
			
			//pdao.salvarPartida(partida);
			Sessao.setPartida(partida, request);
			//request.getSession().setAttribute("partidaAtual", partida);
			return gson.toJson(partida);
			
		//Se houver uma partida em andamento ela será retornada
		}else {
			return gson.toJson(partidaAtual);
		}
	}
	
	@GetMapping(path = "/rodada/nova", produces= { "application/json"})
	public void novaRodada(HttpServletRequest request) {
		Sessao.proxRodada(request);
	}
	
	@PostMapping(path = "/partida/perguntar", produces= { "application/json"})
	public String fazerPergunta(@RequestBody Entrada entrada, HttpServletRequest request) {
		
		Rodada rodadaAtual = Sessao.getRodadaAtual(request);
		//Rodada rodadaAtual = (Rodada) request.getSession().getAttribute("rodadaAtual");
		
		System.out.println(rodadaAtual);
		
		/*Uma rodada tem 3 perguntas*/
		if(rodadaAtual.getPergunta().size()<3){
		
			Pergunta pergunta = pergdao.getPerguntaById(entrada.getId());

			Personagem personagem = rodadaAtual.getFigura_misteriosa();
			String resposta = pergdao.getRespostaPergunta(pergunta, personagem);
			
			rodadaAtual.getPergunta().add(pergunta);
				
			return gson.toJson(resposta);
		}else {
			return gson.toJson(ListaDeMensagens.getMensagem("perguntas da rodada acabaram"));
		}
	}
	
	
	@PostMapping(path = "/partida/tentativa", produces= { "application/json"})
	public String tentativa(@RequestBody Entrada entrada, HttpServletRequest request) {
		
		Rodada rodadaAtual =  Sessao.getRodadaAtual(request);
		Partida partidaAtual = Sessao.getPartida(request);
		//Rodada rodadaAtual = (Rodada) request.getSession().getAttribute("rodadaAtual");
		
		Personagem personagemRodada = rodadaAtual.getFigura_misteriosa();
		
		if(personagemRodada.getId() == entrada.getId()) {
			rodadaAtual.setPontuacao_jogador1(100);
			partidaAtual.setPontuacao_jogador1(partidaAtual.getPontuacao_jogador1()+100);
			
			return gson.toJson(ListaDeMensagens.getMensagem("acertou personagem"));
			
		}else {
			rodadaAtual.setPontuacao_jogador1(0);
			return gson.toJson(ListaDeMensagens.getMensagem("errou personagem"));
		}
	}
	
	@GetMapping(path = "/partida/fim", produces= { "application/json"})
	public String fimPartida(HttpServletRequest request) {
		Partida partida = Sessao.getPartida(request);
		Sessao.setRodadaAtual(null, request);
		Sessao.setPartida(null, request);
		
		//Partida partida = (Partida) request.getSession().getAttribute("partidaAtual");
		//request.getSession().setAttribute("partidaAtual", null);
		//request.getSession().setAttribute("rodadaAtual", null);
		
		return gson.toJson(partida);
	}
}
