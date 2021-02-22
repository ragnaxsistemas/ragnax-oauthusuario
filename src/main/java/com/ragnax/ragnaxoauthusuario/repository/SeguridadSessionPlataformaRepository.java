package com.ragnax.ragnaxoauthusuario.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ragnax.domain.ssosession.entidad.SSOSeguridadSessionPlataforma;


public interface SeguridadSessionPlataformaRepository extends JpaRepository<SSOSeguridadSessionPlataforma, Integer> {
	
	@Query("select r from SSOSeguridadSessionPlataforma r where r.keySeguridadSessionPlataforma = :keySeguridadSessionPlataforma")
	Page<SSOSeguridadSessionPlataforma> findByKeySeguridadSessionPlataforma(String keySeguridadSessionPlataforma, Pageable page);
	
	List<SSOSeguridadSessionPlataforma> findByKeySeguridadSessionPlataformaAndEstadoSeguridadSessionPlataformaAndFechaExpiracionSeguridadSessionPlataformaBetween(
			String keySeguridadSessionPlataforma, Boolean EstadoSeguridadSessionPlataforma, 
			Timestamp limiteInicialFechaExpiracionSeguridadSessionPlataforma,
			Timestamp limiteFinalFechaExpiracionSeguridadSessionPlataforma);
	
	List<SSOSeguridadSessionPlataforma> findByKeySeguridadSessionPlataformaAndEstadoSeguridadSessionPlataforma(
			String keySeguridadSessionPlataforma, Boolean EstadoSeguridadSessionPlataforma);
	
	List<SSOSeguridadSessionPlataforma> findByFechaExpiracionSeguridadSessionPlataformaBetween(Timestamp limiteInicialFechaExpiracionSeguridadSessionPlataforma,
			Timestamp limiteFinalFechaExpiracionSeguridadSessionPlataforma);
	
}
