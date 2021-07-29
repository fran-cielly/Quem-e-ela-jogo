package br.com.QuemEla.control;

import java.util.HashMap;

import java.util.Map;

import br.com.QuemEla.model.Mensagem;

public class ListaDeMensagens {
	static Map<String, Mensagem> listaMensagens = new HashMap<String, Mensagem>();

	public ListaDeMensagens() {
		listaMensagens.put("nao encontrado", new Mensagem(404, "Jogador não encontrado"));
		listaMensagens.put("ok", new Mensagem(200, "Ok"));
		listaMensagens.put("nome usado", new Mensagem(200, "Nome ja utilizado"));
	}
	public static Mensagem getMensagem(String mensagem) {
		return listaMensagens.get(mensagem);
	}
	public static Mensagem getMensagemSucesso() {
		return listaMensagens.get("ok");
	}
}