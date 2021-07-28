package br.com.QuemEla.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tb_personagem")
public class Personagem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nome;
	private String ano_de_nascimento;
	private String local_de_nascimento;
	private String area_de_estudo;
	private String contribuicoes;
	private String curiosidades;
	private String biografia;
	private String foto;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getAno_de_nascimento() {
		return ano_de_nascimento;
	}
	public void setAno_de_nascimento(String ano_de_nascimento) {
		this.ano_de_nascimento = ano_de_nascimento;
	}
	public String getLocal_de_nascimento() {
		return local_de_nascimento;
	}
	public void setLocal_de_nascimento(String local_de_nascimento) {
		this.local_de_nascimento = local_de_nascimento;
	}
	public String getArea_de_estudo() {
		return area_de_estudo;
	}
	public void setArea_de_estudo(String area_de_estudo) {
		this.area_de_estudo = area_de_estudo;
	}
	public String getContribuicoes() {
		return contribuicoes;
	}
	public void setContribuicoes(String contribuicoes) {
		this.contribuicoes = contribuicoes;
	}
	public String getCuriosidades() {
		return curiosidades;
	}
	public void setCuriosidades(String curiosidades) {
		this.curiosidades = curiosidades;
	}
	public String getBiografia() {
		return biografia;
	}
	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}	
}
