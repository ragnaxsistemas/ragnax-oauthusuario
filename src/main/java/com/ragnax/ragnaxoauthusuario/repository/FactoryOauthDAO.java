package com.ragnax.ragnaxoauthusuario.repository;

import com.ragnax.ragnaxoauthusuario.repository.SeguridadSessionPlataformaRepository;

public interface FactoryOauthDAO {
	
	public SeguridadSessionPlataformaRepository getSeguridadSessionPlataformaRepository();
	
	public UsuarioRepository getUsuarioRepository();
	
}
