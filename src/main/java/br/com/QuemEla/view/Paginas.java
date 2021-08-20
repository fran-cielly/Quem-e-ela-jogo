package br.com.QuemEla.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.QuemEla.control.ListaDeMensagens;
import br.com.QuemEla.model.Jogador;
import br.com.QuemEla.sessao.SessaoLogin;

@Controller
public class Paginas {
	
	//tela de login - index
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		Jogador jogador = SessaoLogin.getJogadorLogado();
		if(jogador !=null) {
			return new ModelAndView("home");
		}else {
			return new ModelAndView("index");
		}
	}
	
	//tela de cadastro
	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public ModelAndView cadastro() {
		Jogador jogador = SessaoLogin.getJogadorLogado();
		if(jogador !=null) {
			return new ModelAndView("home");
		}else {
			return new ModelAndView("cadastro");
		}
	}
	
	//home do site
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		Jogador jogador = SessaoLogin.getJogadorLogado();
		if(jogador !=null) {
			return new ModelAndView("home");
		}else {
			return new ModelAndView("index");
		}
	}
	
	//deslogar
	@RequestMapping(value = "/sair", method = RequestMethod.GET)
	public ModelAndView sair() {
		SessaoLogin.setJogadorLogado(null);
		return new ModelAndView("index");	
	}
}
