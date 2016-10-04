package com.kelvearagao.brewer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kelvearagao.brewer.model.Cerveja;
import com.kelvearagao.brewer.repository.Cervejas;

@Service
public class CadastroCervejaService {
	
	@Autowired
	private Cervejas cervejas;
	
	/**
	 * Salva uma cerveja.
	 * 
	 * @param cerveja
	 */
	@Transactional // abre uma transação, pois foi desabilitado a transação automática!
	public void salvar(Cerveja cerveja) {
		cervejas.save(cerveja);
	}
	
}
