package br.com.QuemEla.view;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.QuemEla.control.JogadorDAO;
import br.com.QuemEla.control.ListaDeMensagens;
import br.com.QuemEla.model.Jogador;
import br.com.QuemEla.model.Mensagem;
import br.com.QuemEla.sessao.Sessao;

@RestController
public class FuncoesUsuario {

	Gson gson = new Gson();

	JogadorDAO dao = new JogadorDAO();

	@PostMapping(path = "/usuario/cadastrar", produces = { "application/json" })
	public String novoUsuario(@RequestBody Jogador jogador) {
		
		new ListaDeMensagens();
		
		String nome = jogador.getNome();
		
		if(nome!=null && dao.verificaNome(nome)) {
			dao.salvarJogador(jogador);
			
			Jogador jogadorEncontrado = dao.getJogadorByEmail(nome, jogador.getSenha());
			Sessao.setJogadorLogado(jogadorEncontrado);
			
			return gson.toJson(ListaDeMensagens.getMensagemCadastroSucesso());
		}else{
			return gson.toJson(ListaDeMensagens.getMensagem("nome usado"));
		}
	}

	// @GetMapping(path = "/usuario/listar/{cod}")
	@PostMapping(path = "/usuario/login", produces = { "application/json" })
	public String login(@RequestBody Jogador jogadorlogin) {
		try {
			new ListaDeMensagens();
			Jogador jogador = dao.getJogadorByEmail(jogadorlogin.getNome(), jogadorlogin.getSenha());
			
			if(jogador != null) {
				Sessao.setJogadorLogado(jogador);
				return gson.toJson(jogador);
			}else {
				return gson.toJson(ListaDeMensagens.getMensagem("nao encontrado"));
			}

		} catch (Exception e) {
			return gson.toJson(ListaDeMensagens.getMensagem("erro"));
		}
	}
}
