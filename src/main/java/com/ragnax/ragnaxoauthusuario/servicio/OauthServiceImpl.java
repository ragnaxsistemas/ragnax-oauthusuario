package com.ragnax.ragnaxoauthusuario.servicio;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.ragnax.ragnaxoauthusuario.configuration.FactoryApiProperties;
import com.ragnax.domain.oauthusuario.entidad.Usuario;
import com.ragnax.ragnaxoauthusuario.exception.LogicaImplException;
import com.ragnax.ragnaxoauthusuario.repository.UsuarioRepository;



@Service
@CacheConfig(cacheNames = { "buscarUsuario"})
@ComponentScan(basePackageClasses = { FactoryApiProperties.class})
public class OauthServiceImpl implements OauthService {
	/*****Segun se necesite se van creando llamadas al repositorio para devolver entities.*****/
	private static final Logger LOGGER = LoggerFactory.getLogger(OauthServiceImpl.class);
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private FactoryApiProperties factoryApiProperties;
		
	/***********************************************************/
	/****** Usuario Usuario Usuario *******************/
	/***********************************************************/
	

	@Cacheable(value="buscarUsuario")
	public Usuario buscarUsuario(Usuario objUsuario) throws LogicaImplException{
		
		try {	
			Usuario pageUsername  = usuarioRepository.findByUsername(objUsuario.getUsername());

			/***Si existe reemplazar por el nuevo*/
			if(pageUsername!=null && pageUsername.getId()>0){
				return pageUsername;
			}else {
				throw new LogicaImplException("No existe Usuario con username:" +objUsuario.getUsername());
			}
		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	}	

	/**Generar el metodo de limpieza de cache local y publicar en swagger**/
	public void limpiarCacheLocal() {
		evictBuscarUsuario();
		
	}

	@CacheEvict(value="buscarUsuario", allEntries=true)
	private void evictBuscarUsuario() {
		LOGGER.info(factoryApiProperties.getCache().getEvictBuscarUsuario());
	}

	
	
}
