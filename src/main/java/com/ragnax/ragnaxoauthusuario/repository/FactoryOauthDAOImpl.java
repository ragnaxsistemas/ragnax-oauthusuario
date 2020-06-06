package com.ragnax.ragnaxoauthusuario.repository;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.Repository;

import com.ragnax.ragnaxoauthusuario.repository.SeguridadSessionPlataformaRepository;

@Repository
public class FactoryOauthDAOImpl implements FactoryOauthDAO {

	@Autowired
	private SeguridadSessionPlataformaRepository seguridadSessionPlataformaRepository;	
	
	public SeguridadSessionPlataformaRepository getSeguridadSessionPlataformaRepository() {
		return seguridadSessionPlataformaRepository;
	}
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public UsuarioRepository getUsuarioRepository() {
		return usuarioRepository;
	}
}
