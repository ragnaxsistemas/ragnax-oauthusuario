//package com.ragnax.ragnaxoauthusuario.servicio.cliente;
//
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import com.ragnax.domain.sso.entidad.SSOPerfil;
//import com.ragnax.domain.sso.entidad.SSOUsuario;
//
//
//@FeignClient(name="ragnax-singlesignon" , url = "${server.port.oauthusuario}")
////@FeignClient(name = "ragnax-oauthusuario")
//public interface SinglesignonCliente {
//	/****
//	@GetMapping("/usuarios/search/buscar-username")
//	public Usuario findByUsername(@RequestParam String username);
//	
//	@PutMapping("/usuarios/{id}")
//	public Usuario update(@RequestBody Usuario usuario, @PathVariable Long id);
//	***/
//	
//	@GetMapping("/buscar-perfilxusername/{username}")
//	public SSOPerfil buscarPerfilxUsername(@RequestParam String username);
//	
//	@GetMapping("/buscar-usuarioxperfil/{idperfil}")
//	public SSOUsuario buscarUsuarioxPerfil(@RequestParam String idperfil);
//	
//	
//	
//}
