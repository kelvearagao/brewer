package com.kelvearagao.brewer.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kelvearagao.brewer.model.Cerveja;
import com.kelvearagao.brewer.model.Origem;
import com.kelvearagao.brewer.model.Sabor;
import com.kelvearagao.brewer.repository.Cervejas;
import com.kelvearagao.brewer.repository.Estilos;
import com.kelvearagao.brewer.service.CadastroCervejaService;

@Controller
public class CervejasController {
	
	@Autowired
	private CadastroCervejaService cadastroCervejaService;
	
	@Autowired
	private Estilos estilos;
	
	/**
	 * Retorna a tela de cadastro da cerveja.   
	 *
	 */
	@RequestMapping("/cervejas/novo")
	public ModelAndView novo(Cerveja cerveja) {
		ModelAndView mv = new ModelAndView("cerveja/CadastroCerveja");
		mv.addObject("sabores", Sabor.values());
		mv.addObject("estilos", estilos.findAll());
		mv.addObject("origens", Origem.values());
		
		return mv;
	}
	
	/**
	 * Cadastra uma nova cerveja por meio de uma requisição POST.
	 * Antes de cadastrar é realzado uma validação de acordo com as anotações
	 * na classe Cerveja.
	 * 
	 * @param cerveja - Objeto da classe Cerveja.
	 * @param result - Retorna possíveis erros de validação.
	 * @return String - Retorna a tela de cadastro de cerveja.
	 */
	@RequestMapping(value = "/cervejas/novo", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes) {
		// verifica se tem erros de validação
		if( result.hasErrors() ) {
			return novo(cerveja);
		}
		
		cadastroCervejaService.salvar(cerveja);
		attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso");
		
		return new ModelAndView("redirect:/cervejas/novo");
	}

}
