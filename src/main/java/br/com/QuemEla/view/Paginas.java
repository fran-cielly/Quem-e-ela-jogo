package br.com.QuemEla.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import br.com.QuemEla.model.Jogador;
import br.com.QuemEla.sessao.SessaoLogin;

@Controller
public class Paginas {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView index() {
		return new ModelAndView("index");
	}
	
	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public ModelAndView cadastro() {
		return new ModelAndView("cadastro");
	}
	
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public ModelAndView home() {
		Jogador jogador = SessaoLogin.getJogadorLogado();
		if(jogador !=null) {
			return new ModelAndView("index");
		}else {
			return new ModelAndView("home");
		}
	}
}
