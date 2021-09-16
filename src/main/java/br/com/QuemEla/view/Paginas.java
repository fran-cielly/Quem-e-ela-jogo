package br.com.QuemEla.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.QuemEla.control.ListaDeMensagens;
import br.com.QuemEla.model.Jogador;
import br.com.QuemEla.sessao.Sessao;

@Controller
public class Paginas {
	
	//tela de login - index
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		Jogador jogador = Sessao.getJogadorLogado();
		if(jogador !=null) {
			return new ModelAndView("home");
		}else {
			return new ModelAndView("index");
		}
	}
	
	//tela de cadastro
	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public ModelAndView cadastro() {
		Jogador jogador = Sessao.getJogadorLogado();
		if(jogador !=null) {
			return new ModelAndView("home");
		}else {
			return new ModelAndView("cadastro");
		}
	}
	
	//home do site
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		Jogador jogador = Sessao.getJogadorLogado();
		if(jogador !=null) {
			return new ModelAndView("home");
		}else {
			return new ModelAndView("index");
		}
	}
	
	//deslogar
	@RequestMapping(value = "/sair", method = RequestMethod.GET)
	public ModelAndView sair() {
		Sessao.setJogadorLogado(null);
		return new ModelAndView("index");	
	}
	
	@RequestMapping(value = "/partida", method = RequestMethod.GET)
	public ModelAndView partida() {

		Jogador jogador = Sessao.getJogadorLogado();
		if(jogador !=null) {
			return new ModelAndView("partida");
		}else {
			return new ModelAndView("index");
		}
	}
	
	
	@RequestMapping(value = "/galeria", method = RequestMethod.GET)
	public ModelAndView galeria() {

		Jogador jogador = Sessao.getJogadorLogado();
		if(jogador !=null) {
			return new ModelAndView("galeria");
		}else {
			return new ModelAndView("index");
		}
	}
}
