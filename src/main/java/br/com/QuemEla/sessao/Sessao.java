package br.com.QuemEla.sessao;

import br.com.QuemEla.model.Jogador;
import br.com.QuemEla.model.Partida;

public class Sessao {
	static Jogador jogadorLogado;
	static Partida partidaAtual;
	
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
	}
	
}
