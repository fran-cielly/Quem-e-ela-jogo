package br.com.QuemEla.sessao;

import br.com.QuemEla.model.Jogador;
import br.com.QuemEla.model.Partida;
import br.com.QuemEla.model.Rodada;

public class Sessao {
	static Jogador jogadorLogado;
	static Partida partidaAtual;
	static Rodada rodadaAtual;
	
	public static Jogador getJogadorLogado() {
		return jogadorLogado;
	}
	
	public static void setJogadorLogado(Jogador jogador) {
		jogadorLogado = jogador;
	}
	
	public static Partida getPartida() {
		return partidaAtual;
	}
	
	public static void setPartida(Partida partida) {
		partidaAtual = partida;
		rodadaAtual = partida.getRodadas().get(0);
	}
	
	public static Rodada getRodadaAtual() {
		return rodadaAtual;
	}
	
	public static void setRodadaAtual(Rodada rodada) {
		rodadaAtual = rodada;
	}
	
	public static void proxRodada() {
		int posicao = partidaAtual.getRodadas().indexOf(rodadaAtual);	
		
		System.out.println("posição = "+posicao);
		
		if(posicao < 2) {
			Rodada novaRodada = partidaAtual.getRodadas().get(posicao+1);
			Sessao.setRodadaAtual(novaRodada);
		}
	}
}
