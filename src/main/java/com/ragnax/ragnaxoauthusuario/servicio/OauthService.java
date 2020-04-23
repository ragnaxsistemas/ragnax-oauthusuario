package com.ragnax.ragnaxoauthusuario.servicio;

import com.ragnax.domain.oauthusuario.entidad.Usuario;
import com.ragnax.ragnaxoauthusuario.exception.LogicaImplException;



public interface OauthService {
	
	
	public Usuario buscarUsuario(Usuario objUsuario) throws LogicaImplException;
	
	public void limpiarCacheLocal();
	
}
