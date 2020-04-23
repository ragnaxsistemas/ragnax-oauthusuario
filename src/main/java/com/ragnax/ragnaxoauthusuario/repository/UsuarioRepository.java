package com.ragnax.ragnaxoauthusuario.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ragnax.domain.oauthusuario.entidad.Usuario;


public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
	
	//@Query("select u from Usuario u where u.username = :username")
	Usuario findByUsername(String username);
	
}
