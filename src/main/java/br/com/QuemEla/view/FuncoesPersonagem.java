package br.com.QuemEla.view;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import br.com.QuemEla.control.PersonagemDAO;
@RestController
public class FuncoesPersonagem {
	
	Gson gson = new Gson();
	
	PersonagemDAO pdao = new PersonagemDAO();
	
	@GetMapping(path = "/personagem/listar", produces = { "application/json" })
	public String getPersonagens() {
		return gson.toJson(pdao.listarPersonagens());
	}
	
	@GetMapping(path = "/personagem/listar/id", produces = { "application/json" })
	public String getPersonagemById(@RequestBody int id) {
		return gson.toJson(pdao.getPersonagemById(id));
	}
	
	@GetMapping(path = "/personagem/listar/aleatorio", produces = { "application/json" })
	public String getPersonagemAleatorio() {
		return gson.toJson(pdao.getPersonagemAleatorio());
	}
	
}
