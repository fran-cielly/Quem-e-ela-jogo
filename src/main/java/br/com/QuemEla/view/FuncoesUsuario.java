package br.com.QuemEla.view;

import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.QuemEla.control.JogadorDAO;
import br.com.QuemEla.control.ListaDeMensagens;
import br.com.QuemEla.model.Jogador;
import br.com.QuemEla.model.Mensagem;

@RestController
public class FuncoesUsuario {

	Gson gson = new Gson();

	JogadorDAO dao = new JogadorDAO();

	@PostMapping(path = "/usuario/cadastrar", produces = { "application/json" })
	public String novoUsuario(@RequestBody Jogador jogador) {
		
		new ListaDeMensagens();
		//if(dao.verificaNome(jogador.getNome())) {
			dao.salvarJogador(jogador);
			return gson.toJson(ListaDeMensagens.getMensagemCadastroSucesso());
		/*}else{
			return gson.toJson(ListaDeMensagens.getMensagem("nome usado"));
		}*/
	}

	// @GetMapping(path = "/usuario/listar/{cod}")
	@PostMapping(path = "/usuario/login", produces = { "application/json" })
	public String login(@RequestBody Jogador jogadorlogin) {
		try {
			Jogador jogador = dao.getJogadorByEmail(jogadorlogin.getNome(), jogadorlogin.getSenha());
			return gson.toJson(jogador);

		} catch (Exception e) {
			new ListaDeMensagens();
			return gson.toJson(ListaDeMensagens.getMensagem("nao encontrado"));
		}
	}
}
