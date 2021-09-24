package br.com.QuemEla.sessao;

import javax.servlet.http.HttpServletRequest;

import br.com.QuemEla.model.Jogador;
import br.com.QuemEla.model.Partida;
import br.com.QuemEla.model.Rodada;

public class Sessao {

	public static Jogador getJogadorLogado(HttpServletRequest request) {
		return (Jogador) request.getAttribute("jogadorLogado");
	}
	
	public static void setJogadorLogado(Jogador jogador, HttpServletRequest request) {
		request.setAttribute("jogadorLogado", jogador);
	}
	
	public static Partida getPartida(HttpServletRequest request) {
		return (Partida) request.getAttribute("partidaAtual");
	}
	
	public static void setPartida(Partida partida, HttpServletRequest request) {
		request.setAttribute("partidaAtual", partida);
		request.setAttribute("rodadaAtual", partida.getRodadas().get(0));
	}
	
	public static Rodada getRodadaAtual(HttpServletRequest request) {
		return (Rodada) request.getAttribute("rodadaAtual");
	}
	
	public static void setRodadaAtual(Rodada rodada, HttpServletRequest request) {
		request.setAttribute("rodadaAtual", rodada);
	}
	
	public static void proxRodada(HttpServletRequest request) {
		Partida partidaAtual = (Partida) request.getAttribute("partidaAtual");
		Rodada rodadaAtual = (Rodada) request.getAttribute("rodadaAtual");
		
		int posicao = partidaAtual.getRodadas().indexOf(rodadaAtual);	
		
		if(posicao < 2) {
			Rodada novaRodada = partidaAtual.getRodadas().get(posicao+1);
			
			request.setAttribute("rodadaAtual", novaRodada);
		}
	}
}
