package br.com.QuemEla.model;

public class Mensagem {
	private int cod;
	private String mensagem;
	
	public Mensagem(int cod, String mensagem){
		this.cod = cod;
		this.mensagem = mensagem;
	}
	
	public int getCod() {
		return cod;
	}
	public void setCod(int cod) {
		this.cod = cod;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}
}
