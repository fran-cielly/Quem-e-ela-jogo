package br.com.QuemEla.control;

import java.util.HashMap;

import java.util.Map;

import br.com.QuemEla.model.Mensagem;

public class ListaDeMensagens {
	static Map<String, Mensagem> listaMensagens = new HashMap<String, Mensagem>();

	public ListaDeMensagens() {
		listaMensagens.put("nao encontrado", new Mensagem(404, "Jogador não encontrado"));
		listaMensagens.put("ok", new Mensagem(200, "Ok"));
		listaMensagens.put("cadastrado", new Mensagem(200, "Cadastrado com sucesso"));
		listaMensagens.put("nome usado", new Mensagem(200, "Esse nome já foi usado, escolha outro"));
		listaMensagens.put("erro", new Mensagem(400, "Erro"));
		listaMensagens.put("sair", new Mensagem(400, "Usuário deslogado com sucesso"));
	}
	public static Mensagem getMensagem(String mensagem) {
		return listaMensagens.get(mensagem);
	}
	public static Mensagem getMensagemSucesso() {
		return listaMensagens.get("ok");
	}
	public static Mensagem getMensagemCadastroSucesso() {
		return listaMensagens.get("cadstrado");
	}
	public static Mensagem getMensagemErro() {
		return listaMensagens.get("erro");
	}
}
