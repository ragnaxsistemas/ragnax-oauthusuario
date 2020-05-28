package com.ragnax.ragnaxoauthusuario.servicio;

import com.ragnax.domain.oauthusuario.entidad.Usuario;
import com.ragnax.ragnaxoauthusuario.exception.LogicaImplException;

public interface OauthService {
	
	
	public Usuario buscarUsuario(Usuario objUsuario) throws LogicaImplException; //throws Exception;
	
	public Usuario actualizarUsuario(Long idusuario, Usuario objUsuario) throws LogicaImplException; //throws Exception;
	
	public void limpiarCacheLocal();
	
}
