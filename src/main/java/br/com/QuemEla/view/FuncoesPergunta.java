package br.com.QuemEla.view;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.QuemEla.control.ListaDeMensagens;
import br.com.QuemEla.control.PerguntaDAO;
import br.com.QuemEla.model.Categoria;
import br.com.QuemEla.model.Entrada;
import br.com.QuemEla.model.Pergunta;

@RestController
public class FuncoesPergunta {

	Gson gson = new Gson();
	
	PerguntaDAO pdao = new PerguntaDAO();
	
	@PostMapping(path="/pergunta/cadastrar", produces= { "application/json" })
	public String novaPergunta(@RequestBody Pergunta pergunta ) {
		new ListaDeMensagens();
		
		//Categoria categoria = pdao.getCategoriaById(idCategoria);
		
		//Pergunta pergunta = new Pergunta(texto, categoria);
		try {
			pdao.salvarPergunta(pergunta);
			return gson.toJson(ListaDeMensagens.getMensagemSucesso());
		}catch(Exception e) {
			e.printStackTrace();
			return gson.toJson(ListaDeMensagens.getMensagem("erro"));
		}
	}
	
	@PostMapping(path="/pergunta/listar", produces= { "application/json" })
	public String listarPerguntas() {
		return gson.toJson(pdao.listarPerguntas());
	}
	
	@PostMapping(path="/pergunta/listar/categoria", produces= { "application/json" })
	public String listarPerguntas(@RequestBody Entrada entrada) {
		System.out.println("categoria: "+entrada.getId());
		return gson.toJson(pdao.listarPerguntasByCategoria(entrada.getId()));
	}
	
	//categoria
	
	@PostMapping(path="/categoria/cadastrar", produces= { "application/json" })
	public String novaCategoria(@RequestBody Categoria categoria) {
		new ListaDeMensagens();
		try {
			pdao.salvarCategoria(categoria);
			return gson.toJson(ListaDeMensagens.getMensagemSucesso());
		}catch(Exception e) {
			e.printStackTrace();
			return gson.toJson(ListaDeMensagens.getMensagem("erro"));
		}
	}
	
	@PostMapping(path="/categoria/listar", produces= { "application/json" })
	public String listarCategoria() {
		return gson.toJson(pdao.listarCategorias());
	}
}
