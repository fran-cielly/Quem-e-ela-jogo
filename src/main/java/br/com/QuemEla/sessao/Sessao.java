package br.com.QuemEla.sessao;

import javax.servlet.http.HttpServletRequest;

import br.com.QuemEla.model.Jogador;
import br.com.QuemEla.model.Partida;
import br.com.QuemEla.model.Rodada;

public class Sessao {

	public static Jogador getJogadorLogado(HttpServletRequest request) {
		return (Jogador) request.getSession().getAttribute("jogador");
	}
	
	public static void setJogadorLogado(Jogador jogador, HttpServletRequest request) {
		request.getSession().setAttribute("jogador", jogador);
	}
	
	public static Partida getPartida(HttpServletRequest request) {
		return (Partida) request.getSession().getAttribute("partidaAtual");
	}
	
	public static void setPartida(Partida partida, HttpServletRequest request) {
		request.getSession().setAttribute("partidaAtual", partida);
		if(partida!=null) {
			request.getSession().setAttribute("rodadaAtual", partida.getRodadas().get(0));
		}
		
	}
	
	public static Rodada getRodadaAtual(HttpServletRequest request) {
		return (Rodada) request.getSession().getAttribute("rodadaAtual");
	}
	
	public static void setRodadaAtual(Rodada rodada, HttpServletRequest request) {
		request.getSession().setAttribute("rodadaAtual", rodada);
	}
	
	public static void proxRodada(HttpServletRequest request) {
		Partida partidaAtual = (Partida) request.getSession().getAttribute("partidaAtual");
		Rodada rodadaAtual = (Rodada) request.getSession().getAttribute("rodadaAtual");
		
		int posicao = partidaAtual.getRodadas().indexOf(rodadaAtual);	
		
		if(posicao < 2) {
			Rodada novaRodada = partidaAtual.getRodadas().get(posicao+1);
			
			request.getSession().setAttribute("rodadaAtual", novaRodada);
		}
	}
}
