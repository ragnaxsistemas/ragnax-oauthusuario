package com.ragnax.ragnaxoauthusuario.servicio;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import org.hibernate.HibernateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.*;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ragnax.domain.sso.entidad.Usuario;
import com.ragnax.domain.ssosession.entidad.SSOSeguridadSessionPlataforma;
import com.ragnax.ragnaxoauthusuario.configuration.FactoryApiProperties;
import com.ragnax.ragnaxoauthusuario.exception.LogicaImplException;
import com.ragnax.ragnaxoauthusuario.repository.FactoryOauthDAO;
import com.ragnax.ragnaxutilidades.servicio.ZapalonImpl;
import com.ragnax.ragnaxutilidades.zapala.modelo.ZapalaRequest;

@Service
@CacheConfig(cacheNames = { "buscarUsuario"})
@ComponentScan(basePackageClasses = { FactoryApiProperties.class})
public class OauthServiceImpl implements OauthService {
	/*****Segun se necesite se van creando llamadas al repositorio para devolver entities.*****/
	private static final Logger LOGGER = LoggerFactory.getLogger(OauthServiceImpl.class);
	
	@Autowired
	private FactoryOauthDAO factoryOauthDAO;
	
	@Autowired
	private ZapalonImpl zapalonImpl;
	
	@Autowired
	private FactoryApiProperties factoryApiProperties;
	
	public static final String LOCALE_MAY = "ES";
    
    public static final String LOCALE_MIN = "es";
		
	/***********************************************************/
	/****** Usuario Usuario Usuario *******************/
	/***********************************************************/
	

	@Cacheable(value="buscarUsuario")
	public Usuario buscarUsuario(Usuario objUsuario) throws LogicaImplException{ //throws Exception{
		
		Usuario pageUsername  = null;
		
		try {	
			pageUsername  = factoryOauthDAO.getUsuarioRepository().findByUsername(objUsuario.getUsername());
			/***Si existe reemplazar por el nuevo***/
			if(pageUsername!=null && pageUsername.getId()>0){
				return pageUsername;
			}else {
				LOGGER.info("No existe Usuario con username:" +objUsuario.getUsername());
//				throw new Exception("No existe Usuario con username:" +objUsuario.getUsername());
				throw new LogicaImplException("No existe Usuario con username:" +objUsuario.getUsername());
			}
		} catch (Exception e) {
			LOGGER.info("[Exception] buscarUsuario:" +objUsuario.getUsername());
			//throw new Exception(e.getMessage());
			throw new LogicaImplException(e.getMessage());
		}
		
	}
		
	//Se Busca Por username
	public Usuario actualizarUsuario(Long id, Usuario objUsuario) throws LogicaImplException{ //throws Exception{
		LOGGER.info("actualizarUsuario");
		
		Usuario metUsuario  = null;
		
		try {

			metUsuario  = buscarUsuario(new Usuario(objUsuario.getUsername()));
			
			/***Busqueda por nombre existe en un tipoNegocio No existe. o solo existe en el pageNombreTipoNegocio.idTipoNegocio = id 
				... solo actualizar estado****/
			if((metUsuario!=null && metUsuario.getId().longValue()==id.longValue() )){
				LOGGER.info("save: "+id);
				objUsuario.setId(id);
				metUsuario = factoryOauthDAO.getUsuarioRepository().save(objUsuario);
			}
			else {
				LOGGER.info("No se puede actualizar Usuario con username:" +objUsuario.getUsername());
//				throw new Exception("No se puede actualizar Usuario con username:" +objUsuario.getUsername());
				throw new LogicaImplException("No se puede actualizar Usuario, parametros no existen en un identificador distinto");
			}

		} catch (Exception e) {
			LOGGER.info("[Exception] actualizarUsuario:" +objUsuario.getUsername());
			//throw new Exception(e.getMessage());
			throw new LogicaImplException(e.getMessage());
		}
		
		return buscarUsuario(metUsuario); 
		
	}
	
	/**
	 * @Do crea un objeto AplicacionAccesoServicioEntity desde la base de Datos
	 * @param objeto AplicacionAccesoServicioEntity
	 * @return boolean
	 * @exception throw new BaseException     
	 */
    public SSOSeguridadSessionPlataforma crearSeguridadSessionPlataforma(SSOSeguridadSessionPlataforma seguridadSessionPlataforma ) throws LogicaImplException{
    	// TODO Auto-generated method stub

    	Timestamp tsCreacion = zapalonImpl.DateUtilToTimestamp(new ZapalaRequest(new Date())).getTiempoStamp();
		
    	Timestamp tsExpiracion = zapalonImpl.agregarMinutos(new ZapalaRequest(new Date(), 10)).getTiempoStamp();

    	Integer idSeguridadSessionPlataforma = 0;
    	try {

    		Pageable pageByKeyDesc = PageRequest.of(0, 1, Sort.by("keySeguridadSessionPlataforma").descending());

    		Page<SSOSeguridadSessionPlataforma> pageKeySeguridadSessionPlataforma 
    		= factoryOauthDAO.getSeguridadSessionPlataformaRepository().findByKeySeguridadSessionPlataforma(seguridadSessionPlataforma.getKeySeguridadSessionPlataforma(), pageByKeyDesc);

    		if(pageKeySeguridadSessionPlataforma.isEmpty()){
    			LOGGER.info("no existe Key: Crear");
    			Pageable pageByidDesc = PageRequest.of(0, 1, Sort.by("idSeguridadSessionPlataforma").descending());

    			Page<SSOSeguridadSessionPlataforma> pageIdSeguridadPlataforma = factoryOauthDAO.getSeguridadSessionPlataformaRepository().findAll(pageByidDesc);

    			idSeguridadSessionPlataforma = (!pageIdSeguridadPlataforma.isEmpty()) ? (Integer) pageIdSeguridadPlataforma.getContent().get(0).getIdSeguridadSessionPlataforma() + 1 : 1; 

    		}else {
    			LOGGER.info("si existe Key: Actualizar");
    			/**Actualizar, misma key se cambian las fechas*/

    			idSeguridadSessionPlataforma = pageKeySeguridadSessionPlataforma.getContent().get(0).getIdSeguridadSessionPlataforma();
    		}
    		seguridadSessionPlataforma.setIdSeguridadSessionPlataforma(idSeguridadSessionPlataforma);

    		seguridadSessionPlataforma.setFechaCreacionSeguridadSessionPlataforma(tsCreacion);

    		seguridadSessionPlataforma.setFechaExpiracionSeguridadSessionPlataforma(tsExpiracion);

    		seguridadSessionPlataforma.setEstadoSeguridadSessionPlataforma(true);

    		factoryOauthDAO.getSeguridadSessionPlataformaRepository().save(seguridadSessionPlataforma);

    		return buscarxKeySeguridadSessionPlataforma(seguridadSessionPlataforma);

    	} catch (Exception e) {
    		throw new LogicaImplException(e.getMessage());
    	}
    }
	
	public SSOSeguridadSessionPlataforma buscarxKeySeguridadSessionPlataforma(SSOSeguridadSessionPlataforma objSSOSeguridadSessionPlataforma) throws LogicaImplException {
		LOGGER.info("buscarxKeySeguridadSessionPlataforma: "+objSSOSeguridadSessionPlataforma.getKeySeguridadSessionPlataforma());
		try {
			/*****Como la lista es pequeña, obtener todos desde cache y luego buscar el Id.*****/
			Pageable pageByKeyDesc = PageRequest.of(0, 1, Sort.by("keySeguridadSessionPlataforma").descending());

			Page<SSOSeguridadSessionPlataforma> pageKeySeguridadSessionPlataforma 
				= factoryOauthDAO.getSeguridadSessionPlataformaRepository().findByKeySeguridadSessionPlataforma(objSSOSeguridadSessionPlataforma.getKeySeguridadSessionPlataforma(), pageByKeyDesc);

			/***Busqueda por nombre existe en un tipoNegocio No existe. o solo existe en el pageNombreTipoNegocio.idTipoNegocio = id 
				... solo actualizar estado****/
			if(!pageKeySeguridadSessionPlataforma.isEmpty()){
				LOGGER.info("Si está : "+objSSOSeguridadSessionPlataforma.getKeySeguridadSessionPlataforma());
				return pageKeySeguridadSessionPlataforma.getContent().get(0);
			}
			else {
				LOGGER.info("No  está : "+objSSOSeguridadSessionPlataforma.getKeySeguridadSessionPlataforma());
				throw new LogicaImplException("No existe Rol con nombre:" +objSSOSeguridadSessionPlataforma.getKeySeguridadSessionPlataforma());
			}

		} catch (Exception e) {
			throw new LogicaImplException(e.getMessage());
		}
	}
	
	//Con la key de session obtener el ultimo registro de session almacenado en la base de datos... si hay mas de uno activo... desactualizarlo
	public SSOSeguridadSessionPlataforma listarSeguridadSessionPlataformaxkeyEntityxFechaCreacionxFechaExpiracionEntity(
			SSOSeguridadSessionPlataforma inicialSSOSeguridadSessionPlataforma, SSOSeguridadSessionPlataforma finalSSOSeguridadSessionPlataforma) throws LogicaImplException{
		
		try{		
			List<SSOSeguridadSessionPlataforma> listaSSOSeguridadSessionPlataforma 
			= factoryOauthDAO.getSeguridadSessionPlataformaRepository().findByKeySeguridadSessionPlataformaAndEstadoSeguridadSessionPlataformaAndFechaExpiracionSeguridadSessionPlataformaBetween(
					inicialSSOSeguridadSessionPlataforma.getKeySeguridadSessionPlataforma(), inicialSSOSeguridadSessionPlataforma.getEstadoSeguridadSessionPlataforma(),
					inicialSSOSeguridadSessionPlataforma.getFechaExpiracionSeguridadSessionPlataforma(), finalSSOSeguridadSessionPlataforma.getFechaExpiracionSeguridadSessionPlataforma());
										
			if(listaSSOSeguridadSessionPlataforma==null || listaSSOSeguridadSessionPlataforma.size()==0){
				return null;
			}
			
			int count = 0;
			
			SSOSeguridadSessionPlataforma ssoSeguridadSessionPlataforma = new SSOSeguridadSessionPlataforma();
			
			listaSSOSeguridadSessionPlataforma = ordenarNuevoPorFechaBurbujaDesc(listaSSOSeguridadSessionPlataforma);
			
			for(SSOSeguridadSessionPlataforma sspe:listaSSOSeguridadSessionPlataforma){
				//si existe Key... obtener el primero de la lista de retorno
				if(count==0){
					ssoSeguridadSessionPlataforma = sspe;
				}else{
					sspe.setEstadoSeguridadSessionPlataforma(false);

					factoryOauthDAO.getSeguridadSessionPlataformaRepository().save(ssoSeguridadSessionPlataforma);

				}
				count++;
			}
			
			return ssoSeguridadSessionPlataforma;

		}catch(ParseException ex){
			LOGGER.error("Error se encuentra un dato nulo en la tabla " + ex.getMessage());
			throw new LogicaImplException("Error se encuentra un dato nulo en la tabla ", ex);
		}catch(NullPointerException ex){
			LOGGER.error("Error se encuentra un dato nulo en la tabla " + ex.getMessage());
			throw new LogicaImplException("Error se encuentra un dato nulo en la tabla ", ex);
		}	
		catch(HibernateException ex){
			LOGGER.error("Operación Buscar " + ex.getMessage());
			throw new LogicaImplException("Error de acceso a datos ", ex);
		}
	}
	
	
	public static List<SSOSeguridadSessionPlataforma>  ordenarNuevoPorFechaBurbujaDesc(List<SSOSeguridadSessionPlataforma> 
	listaSSOSeguridadSessionPlataforma) throws ParseException {

		SSOSeguridadSessionPlataforma[] matriz = new SSOSeguridadSessionPlataforma[listaSSOSeguridadSessionPlataforma.size()];
		matriz = listaSSOSeguridadSessionPlataforma.toArray(matriz);

		for (int i = 0; i < matriz.length; i++) {

			for (int j = 0; j < i; j++) {

				Date dFechaJ = new Date(matriz[j].getFechaExpiracionSeguridadSessionPlataforma().getTime());

				Date dFechaI = new Date(matriz[i].getFechaExpiracionSeguridadSessionPlataforma().getTime());

				if (dFechaJ.before(dFechaI)) {
					SSOSeguridadSessionPlataforma seguridadSessionPlataforma = matriz[j];
					matriz[j] = matriz[i];
					matriz[i] = seguridadSessionPlataforma;
				}
			}
		}

		listaSSOSeguridadSessionPlataforma.clear();

		for (int y = 0; y < matriz.length; y++) {
			if(matriz[y].getEstadoSeguridadSessionPlataforma().booleanValue() == true) {
				listaSSOSeguridadSessionPlataforma.add(matriz[y]);
			}
		}

		return listaSSOSeguridadSessionPlataforma;
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
