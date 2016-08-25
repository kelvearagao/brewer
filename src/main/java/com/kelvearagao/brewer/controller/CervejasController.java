package com.kelvearagao.brewer.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CervejasController {
	
	/**
	 * Retorna a tela de cadastro da cerveja.   
	 *
	 */
	@RequestMapping("/cervejas/novo")
	public String novo() {
		return "cerveja/CadastroCerveja";
	}

}
