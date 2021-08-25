package br.com.QuemEla.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_partida")
public class Partida {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private Date data_hora_jogo;
	@OneToOne
	private Jogador jogador1;
	@OneToOne
	private Jogador jogador2;
	private int pontuacao_jogador1;
	private int pontuacao_jogador2;
	@OneToMany
	private List<Rodada> rodadas = new ArrayList<Rodada>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getData_hora_jogo() {
		return data_hora_jogo;
	}
	public void setData_hora_jogo(Date data_hora_jogo) {
		this.data_hora_jogo = data_hora_jogo;
	}
	public Jogador getJogador1() {
		return jogador1;
	}
	public void setJogador1(Jogador jogador1) {
		this.jogador1 = jogador1;
	}
	public Jogador getJogador2() {
		return jogador2;
	}
	public void setJogador2(Jogador jogador2) {
		this.jogador2 = jogador2;
	}
	public int getPontuacao_jogador1() {
		return pontuacao_jogador1;
	}
	public void setPontuacao_jogador1(int pontuacao_jogador1) {
		this.pontuacao_jogador1 = pontuacao_jogador1;
	}
	public int getPontuacao_jogador2() {
		return pontuacao_jogador2;
	}
	public void setPontuacao_jogador2(int pontuacao_jogador2) {
		this.pontuacao_jogador2 = pontuacao_jogador2;
	}
	public List<Rodada> getRodadas() {
		return rodadas;
	}
	public void setRodadas(List<Rodada> rodadas) {
		this.rodadas = rodadas;
	}	
}

