package br.com.QuemEla.view;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.QuemEla.model.Jogador;
import br.com.QuemEla.sessao.Sessao;

@Controller
public class Paginas {
	
	//tela de login - index
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index(HttpServletRequest request) {
		
		Jogador jogador = Sessao.getJogadorLogado(request);
		//Jogador jogador = (Jogador) request.getSession().getAttribute("jogador");
	
		if(jogador !=null) {
			return new ModelAndView("home");
		}else {
			return new ModelAndView("index");
		}
	}
	
	//tela de cadastro
	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public ModelAndView cadastro(HttpServletRequest request) {
		System.err.println("SESSAO->"+request);
		
		Jogador jogador = Sessao.getJogadorLogado(request);
		//Jogador jogador = (Jogador) request.getSession().getAttribute("jogador");
		
		if(jogador !=null) {
			return new ModelAndView("home");
		}else {
			return new ModelAndView("cadastro");
		}
	}
	
	//home do site
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home(HttpServletRequest request) {
		System.err.println("SESSAO->"+request);
		
		Jogador jogador = Sessao.getJogadorLogado(request);
		//Jogador jogador = (Jogador) request.getSession().getAttribute("jogador");
		
		if(jogador !=null) {
			return new ModelAndView("home");
		}else {
			return new ModelAndView("index");
		}
	}
	
	//deslogar
	@RequestMapping(value = "/sair", method = RequestMethod.GET)
	public ModelAndView sair(HttpServletRequest request) {
		System.err.println("SESSAO->"+request);
		
		Sessao.setJogadorLogado(null, request);
		//Jogador jogador = (Jogador) request.getSession().getAttribute("jogador");
		
		return new ModelAndView("index");	
	}
	
	@RequestMapping(value = "/partida", method = RequestMethod.GET)
	public ModelAndView partida(HttpServletRequest request) {

		//Jogador jogador = Sessao.getJogadorLogado( request);
		Jogador jogador = (Jogador) request.getSession().getAttribute("jogador");
		
		if(jogador !=null) {
			return new ModelAndView("partida");
		}else {
			return new ModelAndView("index");
		}
	}
	
	
	@RequestMapping(value = "/galeria", method = RequestMethod.GET)
	public ModelAndView galeria(HttpServletRequest request) {

		Jogador jogador = Sessao.getJogadorLogado(request);
		//Jogador jogador = (Jogador) request.getAttribute("jogador");
		
		if(jogador !=null) {
			return new ModelAndView("galeria");
		}else {
			return new ModelAndView("index");
		}
	}
}
