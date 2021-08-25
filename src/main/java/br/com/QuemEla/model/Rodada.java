package br.com.QuemEla.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_rodada")
public class Rodada {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	private Partida id_partida;
	@OneToOne
	private Personagem figura_misteriosa;
	@OneToOne
	private Pergunta pergunta;
	private int pontuacao_jogador1;
	private int pontuacao_jogador2;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Partida getId_partida() {
		return id_partida;
	}
	public void setId_partida(Partida id_partida) {
		this.id_partida = id_partida;
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
	public Personagem getFigura_misteriosa() {
		return figura_misteriosa;
	}
	public void setFigura_misteriosa(Personagem figura_misteriosa) {
		this.figura_misteriosa = figura_misteriosa;
	}
	public Pergunta getPergunta() {
		return pergunta;
	}
	public void setPergunta(Pergunta pergunta) {
		this.pergunta = pergunta;
	}
}
