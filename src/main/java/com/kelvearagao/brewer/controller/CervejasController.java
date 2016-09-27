package com.kelvearagao.brewer.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kelvearagao.brewer.model.Cerveja;

@Controller
public class CervejasController {
	
	/**
	 * Retorna a tela de cadastro da cerveja.   
	 *
	 */
	@RequestMapping("/cervejas/novo")
	public String novo(Cerveja cerveja) {
		return "cerveja/CadastroCerveja";
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
	public String cadastrar(@Valid Cerveja cerveja, BindingResult result, Model model, RedirectAttributes attributes) {
		// verifica se tem erros de validação
		if( result.hasErrors() ) {
			return novo(cerveja);
		}
		
		attributes.addFlashAttribute("mensagem", "Cerveja salva com sucesso");
		
		System.out.println("Cadastrar " + cerveja.getSku());
		
		return "redirect:/cervejas/novo";
	}

}
