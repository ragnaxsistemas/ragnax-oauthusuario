package com.ragnax.ragnaxoauthusuario.servicio;

import com.ragnax.domain.oauthusuario.entidad.SSOSeguridadSessionPlataforma;
import com.ragnax.domain.oauthusuario.entidad.Usuario;
import com.ragnax.ragnaxoauthusuario.exception.LogicaImplException;

public interface OauthService {
	
	public Usuario buscarUsuario(Usuario objUsuario) throws LogicaImplException; //throws Exception;
	
	public Usuario actualizarUsuario(Long idusuario, Usuario objUsuario) throws LogicaImplException; //throws Exception;
	
	public SSOSeguridadSessionPlataforma crearSeguridadSessionPlataforma(SSOSeguridadSessionPlataforma seguridadSessionPlataforma ) throws LogicaImplException;
	
	public SSOSeguridadSessionPlataforma buscarxKeySeguridadSessionPlataforma(SSOSeguridadSessionPlataforma objSSOSeguridadSessionPlataforma) throws LogicaImplException; 
	
	public SSOSeguridadSessionPlataforma listarSeguridadSessionPlataformaxkeyEntityxFechaCreacionxFechaExpiracionEntity(
			SSOSeguridadSessionPlataforma inicialSSOSeguridadSessionPlataforma, SSOSeguridadSessionPlataforma finalSSOSeguridadSessionPlataforma) throws LogicaImplException;
	
	public void limpiarCacheLocal();
	
}
