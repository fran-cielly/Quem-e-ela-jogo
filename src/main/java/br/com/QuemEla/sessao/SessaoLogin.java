package br.com.QuemEla.sessao;

import br.com.QuemEla.model.Jogador;

public class SessaoLogin {
	static Jogador jogadorLogado;
	public static Jogador getJogadorLogado() {
		return jogadorLogado;
	}
	
	public static void setJogadorLogado(Jogador jogador) {
		jogadorLogado = jogador;
	}
}
