package com.kelvearagao.brewer.controller;

import org.omg.CORBA.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kelvearagao.brewer.model.Cerveja;

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
	
	@RequestMapping(value = "/cervejas/novo", method = RequestMethod.POST)
	public String cadastrar(Cerveja cerveja) {
		System.out.println("Cadastrar " + cerveja.getSku());
		
		return "cerveja/CadastroCerveja";
	}

}
